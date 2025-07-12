package run.moku.modules.gomoku.match.application.usecase.command.start

import org.springframework.stereotype.Service
import run.moku.modules.gomoku.match.application.ports.input.command.MatchCommandInputPort
import run.moku.modules.gomoku.match.application.ports.input.query.MatchQueryInputPort
import run.moku.modules.gomoku.play.domain.model.MokuPlayingModel

@Service
class MatchStartService(
    private val matchCommandInputPort: MatchCommandInputPort,
    private val matchQueryInputPort: MatchQueryInputPort,
) {

    fun start(vararg message: MokuPlayingModel.() -> Unit) {
        MatchStartUsecase.execute() {
            start(
                matchQueryInputPort::canStart,
                matchCommandInputPort::start
            ) {
                message.forEach { action -> action(it) }
            }
        }
    }
}