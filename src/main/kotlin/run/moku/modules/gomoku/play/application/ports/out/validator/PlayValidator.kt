package run.moku.modules.gomoku.play.application.ports.out.validator

import run.moku.modules.gomoku.board.domain.entity.BoardId
import run.moku.modules.gomoku.play.domain.value.MokuPlayStone

interface PlayValidator {
    fun checkTurn(boardId: BoardId, mokuPlayStone: MokuPlayStone)
}