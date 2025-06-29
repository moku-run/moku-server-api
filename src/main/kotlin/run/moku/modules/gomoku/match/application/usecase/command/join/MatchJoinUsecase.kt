package run.moku.modules.gomoku.match.application.usecase.command.join

import run.moku.modules.gomoku.play.domain.entity.MokuPlayer

class MatchJoinUsecase private constructor(
    val player: MokuPlayer
) {

    companion object {
        fun execute(player: MokuPlayer, block: MatchJoinUsecase.() -> Unit) =
            block(MatchJoinUsecase(player))
    }
}