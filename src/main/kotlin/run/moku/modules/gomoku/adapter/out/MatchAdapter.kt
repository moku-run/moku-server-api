package run.moku.modules.gomoku.adapter.out

import org.springframework.stereotype.Service
import run.moku.modules.gomoku.application.ports.out.MatchOutPort
import run.moku.modules.gomoku.domain.entity.player.MokuPlayer
import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue

@Service
class MatchAdapter : MatchOutPort {
    private val queue: Queue<MokuPlayer> = ConcurrentLinkedQueue()

    override fun addQueue(player: MokuPlayer) {
        queue.add(player)
    }

    override fun getSize(): Int = queue.size

    override fun popUser(): MokuPlayer = queue.poll()
}