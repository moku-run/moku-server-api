package run.moku.modules.gomoku.adapter.input.web.controller

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller
import run.moku.modules.gomoku.adapter.input.web.controller.MokuPlayController.Companion.JOIN_PATH
import run.moku.modules.gomoku.application.ports.input.MatchInput
import run.moku.modules.gomoku.application.ports.input.command.MatchCommandInput
import run.moku.modules.gomoku.application.ports.input.query.MatchQueryInput
import run.moku.modules.gomoku.domain.entity.player.MokuPlayer
import run.moku.modules.gomoku.domain.model.MokuPlayingModel
import run.moku.modules.gomoku.domain.value.MokuStone
import java.security.Principal

@Controller
class MokuMatchingController(
    private val matchInput: MatchInput,
    private val messagingTemplate: SimpMessagingTemplate,

    private val matchCommandInput: MatchCommandInput,
    private val matchQueryInput: MatchQueryInput,
) {

    //    @MessageMapping("/ready")
    fun getReady2(
        principal: Principal,
        httpServletResponse: HttpServletResponse,
        httpServletRequest: HttpServletRequest,
    ) {
        matchCommandInput.addQueue(MokuPlayer(principal.name))

        while (matchQueryInput.getQueueSize() >= 2) {
            val mokuPlayingModel = matchInput.join()
            convertAndSendToUser2(mokuPlayingModel)
        }
    }

    private fun convertAndSendToUser2(model: MokuPlayingModel) {
        val blackPlayer = model.getBlackPlayer()
        val whitePlayer = model.getWhitePlayer()

        matchCommandInput.sendToUser(
            blackPlayer, JOIN_PATH, MatchingResultResponse(
                blackPlayer.id,
                model.getBoardIdValue(),
                MokuStone.BLACK_STONE
            )
        )

        matchCommandInput.sendToUser(
            whitePlayer, JOIN_PATH,
            MatchingResultResponse(
                whitePlayer.id,
                model.getBoardIdValue(),
                MokuStone.WHITE_STONE
            )
        )
    }

    @MessageMapping("/ready")
    fun getReady(
        principal: Principal,
        httpServletResponse: HttpServletResponse,
        httpServletRequest: HttpServletRequest,
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
}

data class MatchingResultResponse(
    val id: String,
    val roomId: String,
    val stone: MokuStone
)