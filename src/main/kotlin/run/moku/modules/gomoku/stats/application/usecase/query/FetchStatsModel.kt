package run.moku.modules.gomoku.stats.application.usecase.query

data class FetchStatsModel(
    val winCount: Int,
    val loseCount: Int,
) {
    val totalCount: Int = winCount + loseCount
}