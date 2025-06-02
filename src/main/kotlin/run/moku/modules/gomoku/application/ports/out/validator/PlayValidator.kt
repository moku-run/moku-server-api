package run.moku.modules.gomoku.application.ports.out.validator

import run.moku.modules.gomoku.domain.entity.board.BoardId
import run.moku.modules.gomoku.domain.value.play.MokuPlayStone

interface PlayValidator {
    fun checkTurn(boardId: BoardId, mokuPlayStone: MokuPlayStone)
}