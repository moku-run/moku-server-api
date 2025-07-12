package run.moku.modules.gomoku.play.application.ports.input.query

import run.moku.modules.gomoku.board.domain.entity.BoardId

interface PlayQueryInput {
    fun getDetails(boardId: BoardId)
}