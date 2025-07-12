package run.moku.modules.gomoku.match.application.usecase.command.join

import org.springframework.stereotype.Service
import run.moku.modules.gomoku.match.application.ports.input.command.MatchCommandInputPort
import run.moku.modules.gomoku.play.domain.entity.MokuPlayer

@Service
class MatchJoinService(
    private val matchCommandInput: MatchCommandInputPort,
) {

    fun join(mokuPlayer: MokuPlayer) {
        MatchJoinUsecase.execute(mokuPlayer) {
            addQueue(matchCommandInput::addQueue)
        }
    }
}