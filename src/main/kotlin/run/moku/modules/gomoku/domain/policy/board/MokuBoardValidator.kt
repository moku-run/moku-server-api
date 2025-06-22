package run.moku.modules.gomoku.domain.policy.board

import run.moku.modules.gomoku.domain.entity.player.MokuPlayer
import run.moku.modules.gomoku.domain.value.board.MokuBoard.Companion.DEFAULT_INDEX

class MokuBoardValidator {

    fun validIndex(target: Int): Boolean {
        return target in 0..<DEFAULT_INDEX
    }

    fun validStone(value: Array<Array<MokuPlayer?>>, currentPlayer: MokuPlayer?, row: Int, col: Int): Boolean {
        return value[row][col] == currentPlayer
    }
}