package run.moku.modules.gomoku.board.domain.value

import run.moku.modules.gomoku.play.domain.entity.MokuPlayer
import run.moku.modules.gomoku.board.domain.policy.MokuBoardScanner
import run.moku.modules.gomoku.play.domain.value.MokuPlayResult
import run.moku.modules.gomoku.play.domain.value.MokuPlayStone

class MokuBoard private constructor(
    val value: Array<Array<MokuPlayer?>>
) {
    var currentRow: Int = -1
    var currentCol: Int = -1
    var currentPlayer: MokuPlayer? = null

    fun makeAJudgment(playStone: MokuPlayStone): MokuPlayResult {
        this.currentRow = playStone.getRowIndex()
        this.currentCol = playStone.getColumnIndex()
        this.currentPlayer = playStone.mokuPlayer

        this.value[currentRow][currentCol] = currentPlayer

        return scanBoard()
    }

    private fun scanBoard(): MokuPlayResult {
        val scanner: MokuBoardScanner = MokuBoardScanner.init(this)

        val horizontalCount = scanner.scanHorizontal() + CURRENT_STONE_COUNT
        val verticalCount = scanner.scanVertical() + CURRENT_STONE_COUNT
        val leftDiagonalCount = scanner.scanLeftDiagonal() + CURRENT_STONE_COUNT
        val rightDiagonalCount = scanner.scanRightDiagonal() + CURRENT_STONE_COUNT

        if (horizontalCount == WIN_COUNT ||
            verticalCount == WIN_COUNT ||
            leftDiagonalCount == WIN_COUNT ||
            rightDiagonalCount == WIN_COUNT
        ) {
            return MokuPlayResult.VICTORY
        }

        return MokuPlayResult.IN_PROGRESS
    }

    companion object {

        private const val CURRENT_STONE_COUNT = 1
        private const val WIN_COUNT = 5

        const val DEFAULT_INDEX = 15

        fun init(): MokuBoard =
            MokuBoard(Array(DEFAULT_INDEX) { Array(DEFAULT_INDEX) { null } })
    }
}