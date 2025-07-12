package run.moku.modules.gomoku.stats.application.ports.input.query

import run.moku.modules.gomoku.stats.application.usecase.query.FetchStatsModel
import run.moku.modules.gomoku.player.domain.entity.MokuPlayer

interface FetchStatsInput {
    fun fetchDetails(mokuUser: MokuPlayer): FetchStatsModel
}