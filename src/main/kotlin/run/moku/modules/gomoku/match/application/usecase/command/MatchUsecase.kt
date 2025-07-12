package run.moku.modules.gomoku.match.application.usecase.command

import run.moku.modules.gomoku.play.domain.entity.MokuPlayer

class MatchUsecase private constructor(
    val player: MokuPlayer
) {

    companion object {
        fun execute(player: MokuPlayer, block: MatchUsecase.() -> Unit) =
            block(MatchUsecase(player))
    }
}