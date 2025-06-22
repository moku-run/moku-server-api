package run.moku.modules.gomoku.application.ports.out.command

import run.moku.modules.gomoku.domain.entity.user.MokuUser

interface StatsCommandPort {
    fun init(mokuUser: MokuUser)
}