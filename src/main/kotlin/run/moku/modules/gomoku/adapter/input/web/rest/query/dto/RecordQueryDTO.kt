package run.moku.modules.gomoku.adapter.input.web.rest.query.dto

data class RecordQueryDTO(
    val totalCount: Int,
    val winCount: Int,
    val loseCount: Int,
)