package run.moku.modules.gomoku.adapter.out

import org.springframework.stereotype.Service
import run.moku.modules.gomoku.application.ports.out.PlayOutPort
import run.moku.modules.gomoku.domain.entity.board.BoardId
import run.moku.modules.gomoku.domain.entity.player.BlackStonePlayer
import run.moku.modules.gomoku.domain.entity.player.WhiteStonePlayer
import run.moku.modules.gomoku.domain.model.MokuPlayStatusModel
import run.moku.modules.gomoku.domain.model.MokuPlayingModel
import run.moku.modules.gomoku.domain.value.MokuTurn
import run.moku.modules.gomoku.domain.value.play.MokuPlayStone

@Service
class PlayAdapter(

) : PlayOutPort {

    override fun start(blackStonePlayer: BlackStonePlayer, whiteStonePlayer: WhiteStonePlayer): MokuPlayingModel =
        MokuPlayingModel.start(MokuTurn.of(blackStonePlayer, whiteStonePlayer))
            .let {
                LIST[it.boardId] = it
                return it
            }

    override fun checkTurn(boardId: BoardId, mokuPlayStone: MokuPlayStone) {
        TODO("Not yet implemented")
    }

    override fun play(boardId: BoardId, mokuPlayStone: MokuPlayStone) {
        TODO("Not yet implemented")
    }

    override fun record(boardId: BoardId, mokuPlayStone: MokuPlayStone) {
        TODO("Not yet implemented")
    }

    override fun result(boardId: BoardId): MokuPlayStatusModel {
        TODO("Not yet implemented")
    }

    override fun getModel(boardId: BoardId): MokuPlayingModel =
        LIST[boardId] ?: throw IllegalArgumentException("!!!!")

    companion object {
        private val LIST: MutableMap<BoardId, MokuPlayingModel> = mutableMapOf()
    }
}