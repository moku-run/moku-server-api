package run.moku.modules.gomoku.domain.model

import run.moku.modules.gomoku.domain.entity.player.MokuPlayer
import run.moku.modules.gomoku.domain.value.MokuPlayResult
import run.moku.modules.gomoku.domain.value.MokuTurn

class MokuPlayStatusModel(
    val result: MokuPlayResult,
    val playingModel: MokuPlayingModel,
) {
    fun getBoardValue(): Array<Array<MokuPlayer?>> {
        return this.playingModel.mokuBoard.value
    }

    fun getBlackStonePlayerIdValue(): String = playingModel.getBlackPlayer().id
    fun getWhiteStonePlayerIdValue(): String = playingModel.getWhitePlayer().id
}