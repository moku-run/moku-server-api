package run.moku.modules.gomoku.play.application.ports.out.query

import run.moku.modules.gomoku.board.domain.entity.BoardId
import run.moku.modules.gomoku.play.domain.model.MokuPlayStatusModel
import run.moku.modules.gomoku.play.domain.model.MokuPlayingModel

interface PlayQueryPort {
    fun getModel(boardId: BoardId): MokuPlayingModel
    fun result(boardId: BoardId): MokuPlayStatusModel
}