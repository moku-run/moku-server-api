package run.moku.modules.gomoku.domain.policy.board

import run.moku.modules.gomoku.domain.value.board.MokuBoard

class MokuBoardScanner private constructor(
    private val mokuBoard: MokuBoard
) {
    private val validator: MokuBoardValidator = MokuBoardValidator()

    fun scanHorizontal(): Int = (scan(LEFT_LINE) + scan(RIGHT_LINE))
    fun scanVertical(): Int = (scan(UP_LINE) + scan(DOWN_LINE))
    fun scanLeftDiagonal(): Int = (scan(LEFT_UP_DIAGONAL) + scan(RIGHT_DOWN_DIAGONAL))
    fun scanRightDiagonal(): Int = (scan(RIGHT_UP_DIAGONAL) + scan(LEFT_DOWN_DIAGONAL))

    private fun scan(
        point: Point,
        count: Int = 0,
        depth: Int = 1
    ): Int {
        val addRowValue = point.row * depth
        val addColValue = point.col * depth

        val targetRow = mokuBoard.currentRow + addRowValue
        val targetCol = mokuBoard.currentCol + addColValue

        if (validator.validIndex(targetRow)
            && validator.validIndex(targetCol)
            && validator.validStone(mokuBoard.value, mokuBoard.currentPlayer, targetRow, targetCol)
        ) {
            return scan(
                Point(point.row, point.col),
                count + 1,
                depth + 1
            )
        }

        return count
    }

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

        fun init(mokuBoard: MokuBoard): MokuBoardScanner = MokuBoardScanner(mokuBoard)

        private data class Point(
            val row: Int,
            val col: Int,
        )
    }
}