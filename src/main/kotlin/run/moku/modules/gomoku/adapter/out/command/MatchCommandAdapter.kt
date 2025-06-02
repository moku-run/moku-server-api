package run.moku.modules.gomoku.adapter.out.command

import org.springframework.stereotype.Service
import run.moku.modules.gomoku.adapter.out.infrastructure.repository.MatchConcurrentLinkedQueue
import run.moku.modules.gomoku.application.ports.out.command.MatchCommandPort
import run.moku.modules.gomoku.domain.entity.player.MokuPlayer

@Service
class MatchCommandAdapter(
    private val repository: MatchConcurrentLinkedQueue
) : MatchCommandPort {

    override fun addQueue(player: MokuPlayer) =
        repository.addQueue(player)

    override fun popUser(): MokuPlayer =
        repository.popUser()

    override fun remove(player: MokuPlayer): Unit =
        repository.remove(player)

}