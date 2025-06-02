package run.moku.modules.gomoku.application.usecase.match

import run.moku.modules.gomoku.domain.entity.player.MokuPlayer

class MatchUsecase private constructor(
    val player: MokuPlayer
) {

    companion object {
        fun execute(player: MokuPlayer, block: MatchUsecase.() -> Unit) = block(
            MatchUsecase(player)
        )
    }
}