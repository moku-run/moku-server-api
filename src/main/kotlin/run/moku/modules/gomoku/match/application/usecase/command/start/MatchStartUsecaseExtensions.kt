package run.moku.modules.gomoku.match.application.usecase.command.start

import run.moku.modules.gomoku.play.domain.model.MokuPlayingModel

fun MatchStartUsecase.start(
    canStart: () -> Boolean,
    start: () -> MokuPlayingModel,
    sendMessage: (MokuPlayingModel) -> Unit
) {
    if (canStart()) {
        sendMessage(start.invoke())
    }
}