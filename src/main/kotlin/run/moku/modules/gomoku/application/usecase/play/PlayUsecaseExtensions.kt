package run.moku.modules.gomoku.application.usecase.play

import run.moku.modules.gomoku.domain.entity.board.BoardId
import run.moku.modules.gomoku.domain.model.MokuPlayStatusModel
import run.moku.modules.gomoku.domain.value.play.MokuPlayStone

fun PlayUsecase.checkTurn(check: (BoardId, MokuPlayStone) -> Unit) {
    check(boardId, model)
}

fun PlayUsecase.playStone(play: (BoardId, MokuPlayStone) -> MokuPlayStatusModel) =
    play(boardId, model)

fun PlayUsecase.record(record: (BoardId, MokuPlayStone) -> Unit) =
    record(boardId, model)

fun PlayUsecase.result(load: (BoardId) -> MokuPlayStatusModel): MokuPlayStatusModel =
    load(boardId)