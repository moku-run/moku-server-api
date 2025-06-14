package run.moku.modules.gomoku.application.usecase.stats.query

data class FetchStatsModel(
    val winCount: Int,
    val loseCount: Int,
) {
    val totalCount: Int = winCount + loseCount
}