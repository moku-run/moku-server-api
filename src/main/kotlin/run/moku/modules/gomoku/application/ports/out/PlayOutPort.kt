package run.moku.modules.gomoku.application.ports.out

import run.moku.modules.gomoku.domain.entity.board.BoardId
import run.moku.modules.gomoku.domain.entity.player.BlackStonePlayer
import run.moku.modules.gomoku.domain.entity.player.WhiteStonePlayer
import run.moku.modules.gomoku.domain.model.MokuPlayStatusModel
import run.moku.modules.gomoku.domain.model.MokuPlayingModel
import run.moku.modules.gomoku.domain.value.play.MokuPlayStone

interface PlayOutPort {
    fun start(blackStonePlayer: BlackStonePlayer, whiteStonePlayer: WhiteStonePlayer): MokuPlayingModel
    fun getModel(boardId: BoardId): MokuPlayingModel
    fun checkTurn(boardId: BoardId, mokuPlayStone: MokuPlayStone)
    fun play(boardId: BoardId, mokuPlayStone: MokuPlayStone)
    fun record(boardId: BoardId, mokuPlayStone: MokuPlayStone)
    fun result(boardId: BoardId): MokuPlayStatusModel
}