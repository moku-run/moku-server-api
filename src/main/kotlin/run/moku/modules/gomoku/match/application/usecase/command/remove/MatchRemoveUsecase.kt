package run.moku.modules.gomoku.match.application.usecase.command.remove

import run.moku.modules.gomoku.play.domain.entity.MokuPlayer

class MatchRemoveUsecase private constructor(
    val player: MokuPlayer
) {

    companion object {
        fun execute(player: MokuPlayer, block: MatchRemoveUsecase.() -> Unit) {
            block(MatchRemoveUsecase(player))
        }
    }
}