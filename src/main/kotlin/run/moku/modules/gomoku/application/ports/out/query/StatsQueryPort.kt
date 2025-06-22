package run.moku.modules.gomoku.application.ports.out.query

import run.moku.modules.gomoku.application.usecase.stats.query.FetchStatsModel
import run.moku.modules.gomoku.domain.entity.user.MokuUser

interface StatsQueryPort {
    fun fetchDetails(mokuUser: MokuUser): FetchStatsModel
}