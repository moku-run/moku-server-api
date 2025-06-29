package run.moku.modules.gomoku.play.adapter.input.web.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.event.EventListener
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageExceptionHandler
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.messaging.simp.annotation.SendToUser
import org.springframework.stereotype.Controller
import org.springframework.web.socket.messaging.SessionDisconnectEvent
import run.moku.modules.gomoku.match.application.ports.input.MatchInput
import run.moku.modules.gomoku.play.application.ports.input.PlayInput
import run.moku.modules.gomoku.board.domain.entity.BoardId
import run.moku.modules.gomoku.play.domain.entity.MokuPlayer
import run.moku.modules.gomoku.play.domain.value.MokuPlayResult
import run.moku.modules.gomoku.play.domain.value.MokuStone
import run.moku.modules.gomoku.board.domain.value.ColumnIndex
import run.moku.modules.gomoku.board.domain.value.RowIndex
import run.moku.modules.gomoku.play.domain.value.MokuPlayStone
import java.security.Principal

@Controller
class MokuPlayController(
    private val matchInput: MatchInput,
    private val playInput: PlayInput,
    private val messagingTemplate: SimpMessagingTemplate,
    private val objectMapper: ObjectMapper
) {

    @MessageMapping("/room.{roomId}")
    fun sendMessageGo(
        @DestinationVariable roomId: String,
        principal: Principal,
        message: SubscribeMessage
    ) {

        if (message.type == MessageType.CHAT) {
            messagingTemplate.convertAndSend(
                "/topic/room.$roomId",
                SendMessage(message.type, principal.name, message.content)
            )
            return
        }

        if (message.type == MessageType.PLAY) {
            val row = message.content.split(",")[0].toInt()
            val col = message.content.split(",")[1].toInt()

            println("ROW: $row, COL: $col")

            val boardId = BoardId(roomId)

            val mokuPlayStone = MokuPlayStone(
                RowIndex(row),
                ColumnIndex(col),
                MokuPlayer(principal.name)
            )

            val result = playInput.play(boardId, mokuPlayStone)

            val historyResult = result.getBoardValue()
                .map { row1 ->
                    row1.map { col ->
                        when (col?.id) {
                            result.getBlackStonePlayerIdValue() -> MokuStone.BLACK_STONE
                            result.getWhiteStonePlayerIdValue() -> MokuStone.WHITE_STONE
                            else -> null
                        }
                    }
                }

            val e = result.playingModel.mokuTurn.getCurrentStone()

            val response = PlayResponse(
                result.result,
                principal.name,
                historyResult,
                row,
                col,
                e
            )

            messagingTemplate.convertAndSend(
                "/topic/room.$roomId",
                SendMessage(message.type, principal.name, objectMapper.writeValueAsString(response))
            )
        }
    }

    @EventListener
    fun handle(event: SessionDisconnectEvent) {
        val headers = SimpMessageHeaderAccessor.wrap(event.message)
        val principal = headers.user
        val sessionId = headers.sessionId
        println("SESSION ID: ${event.sessionId}")
        println("principal: $principal")
        println("sessionId: $sessionId")
        println("name: ${principal?.name}")

        principal?.let {
            matchInput.remove(MokuPlayer(it.name))
        }
    }

    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    fun handleException(ex: Throwable): ErrorMessageResponse {
        return ErrorMessageResponse(ex.message)
    }

    companion object {

        data class ErrorMessageResponse(
            val message: String?
        )

        enum class MessageType {
            CHAT(),
            PLAY(),
        }

        data class SubscribeMessage(
            val type: MessageType,
            val content: String
        )

        data class SendMessage(
            val type: MessageType,
            val id: String,
            val content: String
        )

        const val JOIN_PATH = "/queue/matched"
    }
}

class PlayResponse(
    val result: MokuPlayResult,
    val player: String,
    val board: List<List<MokuStone?>>,
    val lastRow: Int,
    val lastCol: Int,
    val currentStone: MokuStone,
    // lastStone,
    // nextStone,
    //
)