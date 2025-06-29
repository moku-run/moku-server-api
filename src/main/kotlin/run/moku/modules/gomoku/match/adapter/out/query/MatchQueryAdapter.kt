package run.moku.modules.gomoku.match.adapter.out.query

import org.springframework.stereotype.Service
import run.moku.modules.gomoku.match.adapter.out.infrastructure.repository.MatchConcurrentLinkedQueue
import run.moku.modules.gomoku.match.application.ports.out.query.MatchQueryPort

@Service
class MatchQueryAdapter(
    private val repository: MatchConcurrentLinkedQueue
) : MatchQueryPort {

    override fun getSize(): Int =
        repository.getSize()
}