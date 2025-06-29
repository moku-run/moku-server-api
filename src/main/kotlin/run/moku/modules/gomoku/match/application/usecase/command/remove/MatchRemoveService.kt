package run.moku.modules.gomoku.match.application.usecase.command.remove

import org.springframework.stereotype.Service
import run.moku.modules.gomoku.match.application.ports.out.command.MatchCommandPort
import run.moku.modules.gomoku.play.domain.entity.MokuPlayer

@Service
class MatchRemoveService(
    private val matchCommandPort: MatchCommandPort,
) {

    fun remove(mokuPlayer: MokuPlayer) {
        MatchRemoveUsecase.execute(mokuPlayer) {
            remove(matchCommandPort::remove)
        }
    }
}