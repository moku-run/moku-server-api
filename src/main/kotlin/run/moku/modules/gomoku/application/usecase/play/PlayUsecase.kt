package run.moku.modules.gomoku.application.usecase.play

import run.moku.modules.gomoku.domain.entity.board.BoardId
import run.moku.modules.gomoku.domain.model.MokuPlayStatusModel
import run.moku.modules.gomoku.domain.value.play.MokuPlayStone

class PlayUsecase private constructor(
    val boardId: BoardId,
    val model: MokuPlayStone
) {

    companion object {
        fun execute(boardId: BoardId, model: MokuPlayStone, block: PlayUsecase.() -> MokuPlayStatusModel) =
            block(
                PlayUsecase(boardId, model)
            )
    }
}