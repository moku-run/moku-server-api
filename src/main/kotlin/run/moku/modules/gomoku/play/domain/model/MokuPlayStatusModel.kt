package run.moku.modules.gomoku.play.domain.model

import run.moku.modules.gomoku.play.domain.entity.MokuPlayer
import run.moku.modules.gomoku.play.domain.value.MokuPlayResult

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