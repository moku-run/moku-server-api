package run.moku.modules.gomoku.application.usecase.stats.command

import run.moku.modules.gomoku.domain.entity.user.MokuUser

fun CreateStatsUsecase.validMokuUser(valid: (MokuUser) -> Unit) =
    valid(user)

fun CreateStatsUsecase.initMokuUserStats(init: (MokuUser) -> Unit) =
    init(user)