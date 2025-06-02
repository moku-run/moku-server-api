package run.moku.modules.gomoku.application.ports.input

import org.springframework.stereotype.Service
import run.moku.modules.gomoku.application.ports.out.command.MatchCommandPort
import run.moku.modules.gomoku.application.ports.out.command.MessageCommandPort
import run.moku.modules.gomoku.application.ports.out.command.PlayCommandPort
import run.moku.modules.gomoku.application.ports.out.query.MatchQueryPort
import run.moku.modules.gomoku.domain.entity.player.BlackStonePlayer
import run.moku.modules.gomoku.domain.entity.player.MokuPlayer
import run.moku.modules.gomoku.domain.entity.player.WhiteStonePlayer
import run.moku.modules.gomoku.domain.model.MokuPlayingModel

@Service
class MatchInput(
    private val matchCommandPort: MatchCommandPort,
    private val matchQueryPort: MatchQueryPort,

    private val playCommandPort: PlayCommandPort,
    private val messageCommandPort: MessageCommandPort
) {

    fun addQueue(player: MokuPlayer) =
        matchCommandPort.addQueue(player)

    fun join(): MokuPlayingModel {
        val player1 = BlackStonePlayer(matchCommandPort.popUser())
        val player2 = WhiteStonePlayer(matchCommandPort.popUser())

        return playCommandPort.start(player1, player2)
    }

    fun remove(mokuPlayer: MokuPlayer) {
        matchCommandPort.remove(mokuPlayer)
    }

    fun getSize(): Int = matchQueryPort.getSize()

    fun <T> sendToUser(
        user: String, path: String, payload: T
    ) {
        messageCommandPort.sendToUser(user, path, payload)
    }
}