package run.moku.modules.gomoku.board.domain.policy

import run.moku.modules.gomoku.play.domain.entity.MokuPlayer
import run.moku.modules.gomoku.board.domain.value.MokuBoard.Companion.DEFAULT_INDEX

class MokuBoardValidator {

    fun validIndex(target: Int): Boolean {
        return target in 0..<DEFAULT_INDEX
    }

    fun validStone(value: Array<Array<MokuPlayer?>>, currentPlayer: MokuPlayer?, row: Int, col: Int): Boolean {
        return value[row][col] == currentPlayer
    }
}