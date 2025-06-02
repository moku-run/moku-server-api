package run.moku.modules.gomoku.domain.value.board

import run.moku.modules.gomoku.domain.entity.player.MokuPlayer
import run.moku.modules.gomoku.domain.value.MokuPlayResult
import run.moku.modules.gomoku.domain.value.play.MokuPlayStone

class MokuBoard private constructor(
    val value: Array<Array<MokuPlayer?>>
) {
    private var currentRow: Int = -1
    private var currentCol: Int = -1
    private var currentPlayer: MokuPlayer? = null

    fun makeAJudgment(playStone: MokuPlayStone): MokuPlayResult {
        this.currentRow = playStone.getRowIndex()
        this.currentCol = playStone.getColumnIndex()
        this.currentPlayer = playStone.mokuPlayer

        this.value[currentRow][currentCol] = currentPlayer

        return scanBoard()
    }

    private fun scanBoard(): MokuPlayResult {
        val horizontalCount = (scan(LEFT_LINE) + scan(RIGHT_LINE)) + CURRENT_STONE_COUNT
        val verticalCount = (scan(UP_LINE) + scan(DOWN_LINE)) + CURRENT_STONE_COUNT
        val leftDiagonalCount = (scan(LEFT_UP_DIAGONAL) + scan(RIGHT_DOWN_DIAGONAL)) + CURRENT_STONE_COUNT
        val rightDiagonalCount = (scan(RIGHT_UP_DIAGONAL) + scan(LEFT_DOWN_DIAGONAL)) + CURRENT_STONE_COUNT

        if (horizontalCount == WIN_COUNT ||
            verticalCount == WIN_COUNT ||
            leftDiagonalCount == WIN_COUNT ||
            rightDiagonalCount == WIN_COUNT
        ) {
            return MokuPlayResult.VICTORY
        }

        return MokuPlayResult.IN_PROGRESS
    }

    private fun scan(point: Point, count: Int = 0, depth: Int = 1): Int {
        val addRowValue = point.row * depth
        val addColValue = point.col * depth

        val targetRow = currentRow + addRowValue
        val targetCol = currentCol + addColValue

        if ((validIndex(targetRow) && validIndex(targetCol) && validStone(targetRow, targetCol))) {
            return scan(Point(point.row, point.col), count + 1, depth + 1)
        }

        return count
    }

    private fun validIndex(target: Int): Boolean =
        target in 0..<DEFAULT_INDEX

    private fun validStone(row: Int, col: Int): Boolean =
        value[row][col] == currentPlayer

    companion object {
        private const val UP = +1
        private const val DOWN = -1
        private const val STAY = 0

        private val LEFT_LINE = Point(STAY, DOWN)
        private val RIGHT_LINE = Point(STAY, UP)

        private val UP_LINE = Point(DOWN, STAY)
        private val DOWN_LINE = Point(UP, STAY)

        private val LEFT_UP_DIAGONAL = Point(DOWN, DOWN)
        private val RIGHT_DOWN_DIAGONAL = Point(UP, UP)

        private val RIGHT_UP_DIAGONAL = Point(UP, DOWN)
        private val LEFT_DOWN_DIAGONAL = Point(DOWN, UP)

        private const val CURRENT_STONE_COUNT = 1
        private const val WIN_COUNT = 5

        const val DEFAULT_INDEX = 15

        fun init(): MokuBoard =
            MokuBoard(Array(DEFAULT_INDEX) { Array(DEFAULT_INDEX) { null } })

        private data class Point(
            val row: Int,
            val col: Int,
        )
    }
}