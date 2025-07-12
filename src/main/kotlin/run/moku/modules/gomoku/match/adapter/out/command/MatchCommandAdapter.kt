package run.moku.modules.gomoku.match.adapter.out.command

import org.springframework.stereotype.Service
import run.moku.modules.gomoku.match.adapter.out.infrastructure.repository.MatchConcurrentLinkedQueue
import run.moku.modules.gomoku.match.application.ports.out.command.MatchCommandPort
import run.moku.modules.gomoku.play.domain.entity.MokuPlayer

@Service
class MatchCommandAdapter(
    private val repository: MatchConcurrentLinkedQueue
) : MatchCommandPort {

    override fun addQueue(player: MokuPlayer) {
        repository
            .isContains(player)
            .let { repository.addQueue(player) }
    }

    override fun popUser(): MokuPlayer =
        repository.popUser()

    override fun remove(player: MokuPlayer): Unit =
        repository.remove(player)

}