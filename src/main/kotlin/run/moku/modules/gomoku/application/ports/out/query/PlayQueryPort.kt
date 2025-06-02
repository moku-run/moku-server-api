package run.moku.modules.gomoku.application.ports.out.query

import run.moku.modules.gomoku.domain.entity.board.BoardId
import run.moku.modules.gomoku.domain.model.MokuPlayStatusModel
import run.moku.modules.gomoku.domain.model.MokuPlayingModel

interface PlayQueryPort {
    fun getModel(boardId: BoardId): MokuPlayingModel
    fun result(boardId: BoardId): MokuPlayStatusModel
}