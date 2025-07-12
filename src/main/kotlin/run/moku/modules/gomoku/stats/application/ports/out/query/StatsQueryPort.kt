package run.moku.modules.gomoku.stats.application.ports.out.query

import run.moku.modules.gomoku.stats.application.usecase.query.FetchStatsModel
import run.moku.modules.gomoku.player.domain.entity.MokuPlayer

interface StatsQueryPort {
    fun fetchDetails(mokuUser: MokuPlayer): FetchStatsModel
}