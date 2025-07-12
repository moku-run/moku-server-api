package run.moku.modules.gomoku.match.adapter.input.web.controller

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import run.moku.modules.gomoku.match.application.ports.out.command.MessageCommandPort
import run.moku.modules.gomoku.match.application.usecase.command.join.MatchJoinService
import run.moku.modules.gomoku.match.application.usecase.command.start.MatchStartService
import run.moku.modules.gomoku.play.adapter.input.web.controller.MokuPlayController.Companion.JOIN_PATH
import run.moku.modules.gomoku.play.domain.entity.MokuPlayer
import run.moku.modules.gomoku.play.domain.model.MokuPlayingModel
import run.moku.modules.gomoku.play.domain.value.MokuStone
import java.security.Principal

@Controller
class MokuMatchingController(
    private val matchJoinService: MatchJoinService,
    private val matchService: MatchStartService,

    private val messageCommandPort: MessageCommandPort,
) {

    @MessageMapping("/ready")
    fun getReady(
        principal: Principal
    ) {
        matchJoinService.join(MokuPlayer(principal.name))
        matchService.start(
            ::convertAndSendToWhitePlayer,
            ::convertAndSendToBlackPlayer,
        )
    }

    private fun convertAndSendToWhitePlayer(model: MokuPlayingModel) {
        val whitePlayer = model.getWhitePlayer()

        send(whitePlayer, model, MokuStone.WHITE_STONE)
    }

    private fun convertAndSendToBlackPlayer(model: MokuPlayingModel) {
        val blackPlayer = model.getBlackPlayer()

        send(blackPlayer, model, MokuStone.BLACK_STONE)
    }

    private fun send(
        player: MokuPlayer,
        model: MokuPlayingModel,
        stone: MokuStone
    ) {
        messageCommandPort.send(
            player,
            JOIN_PATH,
            MatchingResultResponse(player.id, model.getBoardIdValue(), stone)
        )
    }
}

data class MatchingResultResponse(
    val id: String,
    val roomId: String,
    val stone: MokuStone
)