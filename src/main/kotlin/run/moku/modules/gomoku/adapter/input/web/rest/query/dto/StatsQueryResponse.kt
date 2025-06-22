package run.moku.modules.gomoku.adapter.input.web.rest.query.dto

interface StatsQueryResponse {

    data class Details(
        val totalCount: Int,
        val winCount: Int,
        val loseCount: Int,
    ) {
        val onTheGo get() = loseCount > 1
    }
}

fun main() {
    val e = StatsQueryResponse.Details(1, 2, 3)
    e.onTheGo
}