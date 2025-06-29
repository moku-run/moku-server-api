package run.moku.modules.gomoku.play.application.usecase.command

import run.moku.modules.gomoku.board.domain.entity.BoardId
import run.moku.modules.gomoku.play.domain.model.MokuPlayStatusModel
import run.moku.modules.gomoku.play.domain.value.MokuPlayStone

class PlayUsecase private constructor(
    val boardId: BoardId,
    val model: MokuPlayStone
) {

    companion object {
        fun execute(boardId: BoardId, model: MokuPlayStone, block: PlayUsecase.() -> MokuPlayStatusModel) =
            block(PlayUsecase(boardId, model))
    }
}