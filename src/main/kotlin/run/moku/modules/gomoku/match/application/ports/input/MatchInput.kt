package run.moku.modules.gomoku.match.application.ports.input

import org.springframework.stereotype.Service
import run.moku.modules.gomoku.match.application.ports.out.command.MatchCommandPort
import run.moku.modules.gomoku.match.application.ports.out.query.MatchQueryPort
import run.moku.modules.gomoku.play.application.ports.out.command.PlayCommandPort
import run.moku.modules.gomoku.play.domain.entity.BlackStonePlayer
import run.moku.modules.gomoku.play.domain.entity.MokuPlayer
import run.moku.modules.gomoku.play.domain.entity.WhiteStonePlayer
import run.moku.modules.gomoku.play.domain.model.MokuPlayingModel

@Service
class MatchInput(
    private val matchCommandPort: MatchCommandPort,
    private val matchQueryPort: MatchQueryPort,

    private val playCommandPort: PlayCommandPort,
) {

    fun addQueue(player: MokuPlayer) {
        matchCommandPort.addQueue(player)
    }

    fun join(): MokuPlayingModel {
        val player1 = BlackStonePlayer(matchCommandPort.popUser())
        val player2 = WhiteStonePlayer(matchCommandPort.popUser())

        return playCommandPort.start(player1, player2)
    }

    fun remove(mokuPlayer: MokuPlayer) {
        matchCommandPort.remove(mokuPlayer)
    }

    fun getSize(): Int = matchQueryPort.getSize()
}