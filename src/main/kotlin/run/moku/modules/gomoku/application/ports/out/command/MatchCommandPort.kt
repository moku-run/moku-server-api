package run.moku.modules.gomoku.application.ports.out.command

import run.moku.modules.gomoku.domain.entity.player.MokuPlayer

interface MatchCommandPort {
    fun addQueue(player: MokuPlayer)
    fun remove(player: MokuPlayer)
    fun popUser(): MokuPlayer
}