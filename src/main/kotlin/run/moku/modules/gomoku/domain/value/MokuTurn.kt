package run.moku.modules.gomoku.domain.value

import run.moku.modules.gomoku.domain.entity.player.BlackStonePlayer
import run.moku.modules.gomoku.domain.entity.player.MokuPlayer
import run.moku.modules.gomoku.domain.entity.player.WhiteStonePlayer
import run.moku.modules.gomoku.domain.value.play.MokuPlayStone

class MokuTurn private constructor(
    val blackStonePlayer: BlackStonePlayer,
    val whiteStonePlayer: WhiteStonePlayer,

    var currentPlayer: MokuPlayer = blackStonePlayer.player,
) {

    fun change(playStone: MokuPlayStone) {
        if (playStone.mokuPlayer != currentPlayer) {
            throw IllegalArgumentException("차례가 아닙니다.")
        }

        this.currentPlayer = playerToggle()
    }

    private fun playerToggle(): MokuPlayer =
        if (currentPlayer == blackStonePlayer.player) whiteStonePlayer.player else blackStonePlayer.player

    companion object {
        fun of(blackStonePlayer: BlackStonePlayer, whiteStonePlayer: WhiteStonePlayer): MokuTurn =
            MokuTurn(blackStonePlayer, whiteStonePlayer)
    }
}