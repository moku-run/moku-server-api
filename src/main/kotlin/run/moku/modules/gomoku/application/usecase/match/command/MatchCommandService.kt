package run.moku.modules.gomoku.application.usecase.match.command

import org.springframework.stereotype.Service
import run.moku.modules.gomoku.application.ports.input.command.MatchCommandInput
import run.moku.modules.gomoku.application.ports.out.command.MatchCommandPort
import run.moku.modules.gomoku.application.ports.out.command.MessageCommandPort
import run.moku.modules.gomoku.application.ports.out.command.PlayCommandPort
import run.moku.modules.gomoku.domain.entity.player.BlackStonePlayer
import run.moku.modules.gomoku.domain.entity.player.MokuPlayer
import run.moku.modules.gomoku.domain.entity.player.WhiteStonePlayer
import run.moku.modules.gomoku.domain.model.MokuPlayingModel

@Service
class MatchCommandService(
    private val matchCommandPort: MatchCommandPort,
    private val playCommandPort: PlayCommandPort,
    private val messageCommandPort: MessageCommandPort
) : MatchCommandInput {

    override fun addQueue(player: MokuPlayer) {
        matchCommandPort.addQueue(player)
    }

    override fun start(): MokuPlayingModel {
        val player1 = BlackStonePlayer(matchCommandPort.popUser())
        val player2 = WhiteStonePlayer(matchCommandPort.popUser())

        return playCommandPort.start(player1, player2)
    }

    override fun <T> sendToUser(player: MokuPlayer, path: String, payload: T) {
        messageCommandPort.send(player, path, payload)
    }
}