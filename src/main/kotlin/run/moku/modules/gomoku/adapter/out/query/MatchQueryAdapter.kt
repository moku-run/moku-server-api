package run.moku.modules.gomoku.adapter.out.query

import org.springframework.stereotype.Service
import run.moku.modules.gomoku.adapter.out.infrastructure.repository.MatchConcurrentLinkedQueue
import run.moku.modules.gomoku.application.ports.out.query.MatchQueryPort

@Service
class MatchQueryAdapter(
    private val repository: MatchConcurrentLinkedQueue
) : MatchQueryPort {

    override fun getSize(): Int =
        repository.getSize()
}