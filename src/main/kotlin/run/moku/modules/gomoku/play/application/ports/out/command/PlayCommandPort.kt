package run.moku.modules.gomoku.play.application.ports.out.command

import run.moku.modules.gomoku.board.domain.entity.BoardId
import run.moku.modules.gomoku.play.domain.entity.BlackStonePlayer
import run.moku.modules.gomoku.play.domain.entity.WhiteStonePlayer
import run.moku.modules.gomoku.play.domain.model.MokuPlayStatusModel
import run.moku.modules.gomoku.play.domain.model.MokuPlayingModel
import run.moku.modules.gomoku.play.domain.value.MokuPlayStone

interface PlayCommandPort {
    fun start(blackStonePlayer: BlackStonePlayer, whiteStonePlayer: WhiteStonePlayer): MokuPlayingModel
    fun play(boardId: BoardId, mokuPlayStone: MokuPlayStone): MokuPlayStatusModel
    fun record(boardId: BoardId, mokuPlayStone: MokuPlayStone)
    fun removeBoard(boardId: BoardId)
}