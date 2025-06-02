package run.moku.modules.gomoku.domain.model

import run.moku.modules.gomoku.domain.entity.board.BoardId
import run.moku.modules.gomoku.domain.entity.player.MokuPlayer
import run.moku.modules.gomoku.domain.value.MokuTurn
import run.moku.modules.gomoku.domain.value.board.MokuBoard
import run.moku.modules.gomoku.domain.value.play.MokuPlayHistory
import run.moku.modules.gomoku.domain.value.play.MokuPlayStone

class MokuPlayingModel private constructor(
    var mokuTurn: MokuTurn,

    val boardId: BoardId = BoardId.init(),
    val mokuBoard: MokuBoard = MokuBoard.init(),
    val mokuHistory: MokuPlayHistory = MokuPlayHistory.init(),
) {

    fun playStone(playStone: MokuPlayStone): MokuPlayStatusModel {
        mokuTurn.change(playStone)
        mokuHistory.record(playStone)
        val result = mokuBoard.makeAJudgment(playStone)

        return MokuPlayStatusModel(result,this)
    }

    fun getBoardIdValue() =
        boardId.value

    fun getCurrentPlayer() =
        mokuTurn.currentPlayer

    fun getBlackPlayer(): MokuPlayer {
        return mokuTurn.blackStonePlayer.player
    }

    fun getWhitePlayer(): MokuPlayer {
        return mokuTurn.whiteStonePlayer.player
    }

    companion object {
        fun start(mokuTurn: MokuTurn): MokuPlayingModel =
            MokuPlayingModel(mokuTurn = mokuTurn)
    }
}