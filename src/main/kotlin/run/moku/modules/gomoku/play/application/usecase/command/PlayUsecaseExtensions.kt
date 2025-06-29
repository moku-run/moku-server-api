package run.moku.modules.gomoku.play.application.usecase.command

import run.moku.modules.gomoku.board.domain.entity.BoardId
import run.moku.modules.gomoku.play.domain.model.MokuPlayStatusModel
import run.moku.modules.gomoku.play.domain.value.MokuPlayStone

fun PlayUsecase.checkTurn(check: (BoardId, MokuPlayStone) -> Unit) {
    check(boardId, model)
}

fun PlayUsecase.playStone(play: (BoardId, MokuPlayStone) -> MokuPlayStatusModel) =
    play(boardId, model)

fun PlayUsecase.record(record: (BoardId, MokuPlayStone) -> Unit) =
    record(boardId, model)

fun PlayUsecase.result(load: (BoardId) -> MokuPlayStatusModel): MokuPlayStatusModel =
    load(boardId)