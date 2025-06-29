package run.moku.modules.gomoku.stats.application.ports.out.command

import run.moku.modules.gomoku.player.domain.entity.MokuPlayer

interface StatsCommandPort {
    fun init(mokuUser: MokuPlayer)
}