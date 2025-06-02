package run.moku.modules.gomoku.adapter.out.infrastructure.repository

import org.springframework.stereotype.Service
import run.moku.modules.gomoku.domain.entity.player.MokuPlayer
import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue

@Service
class MatchConcurrentLinkedQueue {

    fun addQueue(player: MokuPlayer) {
        QUEUE.add(player)
    }

    fun popUser(): MokuPlayer {
        return QUEUE.poll()
    }

    fun getSize(): Int {
        return QUEUE.size
    }

    fun remove(player: MokuPlayer) {
        QUEUE.removeIf { it == player }
    }

    companion object {
        private val QUEUE: Queue<MokuPlayer> = ConcurrentLinkedQueue()
    }
}