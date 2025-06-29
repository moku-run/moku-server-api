package run.moku.modules.gomoku.stats.application.ports.out.validator

import run.moku.modules.gomoku.player.domain.entity.MokuPlayer

interface MokuUserValidator {
    fun validate(mokuUser: MokuPlayer)
}