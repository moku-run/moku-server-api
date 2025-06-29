package run.moku.modules.gomoku.match.adapter.out.query

import org.springframework.stereotype.Service
import run.moku.modules.gomoku.match.application.ports.input.query.MatchQueryInput

@Service
class MatchQueryInputAdapter(

) : MatchQueryInput {
    override fun getQueueSize(): Int {
        TODO("Not yet implemented")
    }
}