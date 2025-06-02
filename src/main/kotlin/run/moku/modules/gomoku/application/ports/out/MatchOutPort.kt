package run.moku.modules.gomoku.application.ports.out

import run.moku.modules.gomoku.domain.entity.player.MokuPlayer

interface MatchOutPort {
    fun addQueue(player: MokuPlayer)
    fun getSize(): Int
    fun popUser(): MokuPlayer
}