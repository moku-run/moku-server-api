package run.moku.modules.gomoku.domain.value.board

import run.moku.framework.adapter.validator.throwIf
import run.moku.framework.api.exception.ApiException
import run.moku.framework.api.response.ApiResponseCode

@JvmInline
value class RowIndex(
    val value: Int
) {

    init {
        throwIf(value > MokuBoard.DEFAULT_INDEX, ApiException(ApiResponseCode.BOARD_INVALID_ROW_SIZE))
    }
}