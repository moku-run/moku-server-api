package run.moku.modules.gomoku.application.ports.input

import run.moku.modules.gomoku.application.usecase.stats.query.FetchStatsModel
import run.moku.modules.gomoku.domain.entity.user.MokuUser

interface FetchStatsInput {
    fun fetchDetails(mokuUser: MokuUser): FetchStatsModel
}