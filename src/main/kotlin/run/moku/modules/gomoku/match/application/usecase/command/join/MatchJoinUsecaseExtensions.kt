package run.moku.modules.gomoku.match.application.usecase.command.join

import run.moku.modules.gomoku.play.domain.entity.MokuPlayer


fun MatchJoinUsecase.addQueue(add: (MokuPlayer) -> Unit) {
    add(player)
}