package run.moku.modules.gomoku.adapter.input.web.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.context.event.EventListener
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageExceptionHandler
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.messaging.simp.annotation.SendToUser
import org.springframework.stereotype.Controller
import org.springframework.web.socket.messaging.SessionDisconnectEvent
import run.moku.modules.gomoku.application.ports.input.MatchInput
import run.moku.modules.gomoku.application.ports.input.PlayInput
import run.moku.modules.gomoku.domain.entity.board.BoardId
import run.moku.modules.gomoku.domain.entity.player.MokuPlayer
import run.moku.modules.gomoku.domain.model.MokuPlayingModel
import run.moku.modules.gomoku.domain.value.MokuPlayResult
import run.moku.modules.gomoku.domain.value.MokuStone
import run.moku.modules.gomoku.domain.value.board.ColumnIndex
import run.moku.modules.gomoku.domain.value.board.RowIndex
import run.moku.modules.gomoku.domain.value.play.MokuPlayStone
import java.security.Principal

@Controller
class MokuPlayController(
    private val matchInput: MatchInput,
    private val playInput: PlayInput,
    private val messagingTemplate: SimpMessagingTemplate
) {

    @MessageMapping("/ready")
    fun getReady(
        principal: Principal
    ) {
        matchInput.addQueue(MokuPlayer(principal.name))

        while (matchInput.getSize() >= 2) {
            val mokuPlayingModel = matchInput.join()
            convertAndSendToUser(mokuPlayingModel)
        }
    }

    private fun convertAndSendToUser(model: MokuPlayingModel) {
        val blackPlayer = model.getBlackPlayer()
        val whitePlayer = model.getWhitePlayer()

        messagingTemplate.convertAndSendToUser(
            blackPlayer.id,
            JOIN_PATH,
            MatchingResultResponse(
                blackPlayer.id,
                model.getBoardIdValue(),
                MokuStone.BLACK_STONE
            )
        )

        messagingTemplate.convertAndSendToUser(
            whitePlayer.id,
            JOIN_PATH,
            MatchingResultResponse(
                whitePlayer.id,
                model.getBoardIdValue(),
                MokuStone.WHITE_STONE
            )
        )
    }

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

            val result = playInput.play(
                boardId, MokuPlayStone(
                    RowIndex(row),
                    ColumnIndex(col),
                    MokuPlayer(principal.name)
                )
            )

            val jacksonObjectMapper = jacksonObjectMapper()

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

            val response = PlayResponse(
                result.result,
                historyResult,
                principal.name
            )

            messagingTemplate.convertAndSend(
                "/topic/room.$roomId",
                SendMessage(message.type, principal.name, jacksonObjectMapper.writeValueAsString(response))
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

data class MatchingResultResponse(
    val id: String,
    val roomId: String,
    val stone: MokuStone
)

class PlayResponse(
    val result: MokuPlayResult,
    val board: List<List<MokuStone?>>,
    val player: String,
)