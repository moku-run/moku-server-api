package run.moku.modules.gomoku.play.domain.value

import run.moku.modules.gomoku.play.domain.entity.BlackStonePlayer
import run.moku.modules.gomoku.play.domain.entity.MokuPlayer
import run.moku.modules.gomoku.play.domain.entity.WhiteStonePlayer

class MokuTurn private constructor(
    val blackStonePlayer: BlackStonePlayer,
    val whiteStonePlayer: WhiteStonePlayer,

    var currentPlayer: MokuPlayer = blackStonePlayer.player,
) {

    fun getCurrentStone(): MokuStone {
        if (blackStonePlayer.player == currentPlayer) return MokuStone.BLACK_STONE
        return MokuStone.WHITE_STONE
    }

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