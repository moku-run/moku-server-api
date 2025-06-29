package run.moku.modules.gomoku.match.adapter.out.query

import org.springframework.stereotype.Service
import run.moku.modules.gomoku.match.application.ports.input.query.MatchQueryInputPort
import run.moku.modules.gomoku.match.application.ports.out.query.MatchQueryPort

@Service
class MatchQueryInputAdapter(
    private val matchQueryPort: MatchQueryPort,
) : MatchQueryInputPort {

    override fun canStart(): Boolean = matchQueryPort.getSize() >= 2
}