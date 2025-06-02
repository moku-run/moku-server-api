package run.moku.modules.gomoku.domain.value.play

import run.moku.modules.gomoku.domain.entity.player.MokuPlayer
import run.moku.modules.gomoku.domain.value.board.ColumnIndex
import run.moku.modules.gomoku.domain.value.board.RowIndex

data class MokuPlayStone(
    private val rowIndex: RowIndex,
    private val columnIndex: ColumnIndex,
    val mokuPlayer: MokuPlayer
) {

    fun getColumnIndex() = columnIndex.value
    fun getRowIndex() = rowIndex.value
}