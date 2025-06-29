package run.moku.modules.gomoku.match.application.usecase.command

import org.springframework.stereotype.Service
import run.moku.modules.gomoku.match.application.ports.input.command.MatchCommandInputPort
import run.moku.modules.gomoku.match.application.ports.out.command.MatchCommandPort
import run.moku.modules.gomoku.play.application.ports.out.command.PlayCommandPort
import run.moku.modules.gomoku.play.domain.entity.BlackStonePlayer
import run.moku.modules.gomoku.play.domain.entity.MokuPlayer
import run.moku.modules.gomoku.play.domain.entity.WhiteStonePlayer
import run.moku.modules.gomoku.play.domain.model.MokuPlayingModel

@Service
class MatchCommandService(
    private val matchCommandPort: MatchCommandPort,
    private val playCommandPort: PlayCommandPort,
) : MatchCommandInputPort {

    override fun addQueue(player: MokuPlayer) {
        matchCommandPort.addQueue(player)
    }

    override fun start(): MokuPlayingModel {
        val player1 = BlackStonePlayer(matchCommandPort.popUser())
        val player2 = WhiteStonePlayer(matchCommandPort.popUser())

        return playCommandPort.start(player1, player2)
    }
}