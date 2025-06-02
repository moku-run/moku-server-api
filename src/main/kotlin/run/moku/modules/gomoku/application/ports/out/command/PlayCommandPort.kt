package run.moku.modules.gomoku.application.ports.out.command

import run.moku.modules.gomoku.domain.entity.board.BoardId
import run.moku.modules.gomoku.domain.entity.player.BlackStonePlayer
import run.moku.modules.gomoku.domain.entity.player.WhiteStonePlayer
import run.moku.modules.gomoku.domain.model.MokuPlayStatusModel
import run.moku.modules.gomoku.domain.model.MokuPlayingModel
import run.moku.modules.gomoku.domain.value.play.MokuPlayStone

interface PlayCommandPort {
    fun start(blackStonePlayer: BlackStonePlayer, whiteStonePlayer: WhiteStonePlayer): MokuPlayingModel
    fun play(boardId: BoardId, mokuPlayStone: MokuPlayStone): MokuPlayStatusModel
    fun record(boardId: BoardId, mokuPlayStone: MokuPlayStone)
    fun removeBoard(boardId: BoardId)
}