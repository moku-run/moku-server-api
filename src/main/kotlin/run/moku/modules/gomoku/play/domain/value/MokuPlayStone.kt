package run.moku.modules.gomoku.play.domain.value

import run.moku.modules.gomoku.play.domain.entity.MokuPlayer
import run.moku.modules.gomoku.board.domain.value.ColumnIndex
import run.moku.modules.gomoku.board.domain.value.RowIndex

data class MokuPlayStone(
    private val rowIndex: RowIndex,
    private val columnIndex: ColumnIndex,
    val mokuPlayer: MokuPlayer
) {

    fun getColumnIndex() = columnIndex.value
    fun getRowIndex() = rowIndex.value
}