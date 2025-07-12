package run.moku.modules.gomoku.match.application.ports.out.command

import run.moku.modules.gomoku.play.domain.entity.MokuPlayer

interface MatchCommandPort {
    fun addQueue(player: MokuPlayer)
    fun remove(player: MokuPlayer)
    fun popUser(): MokuPlayer
}