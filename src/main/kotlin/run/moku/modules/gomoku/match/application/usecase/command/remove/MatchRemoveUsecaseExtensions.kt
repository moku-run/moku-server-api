package run.moku.modules.gomoku.match.application.usecase.command.remove

import run.moku.modules.gomoku.play.domain.entity.MokuPlayer

fun MatchRemoveUsecase.remove(remove: (MokuPlayer) -> Unit) {
    remove(player)
}