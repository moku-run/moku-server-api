package run.moku.modules.gomoku.adapter.out.command

import org.springframework.stereotype.Service
import run.moku.modules.gomoku.adapter.out.infrastructure.repository.PlayConcurrentMap
import run.moku.modules.gomoku.application.ports.out.command.PlayCommandPort
import run.moku.modules.gomoku.domain.entity.board.BoardId
import run.moku.modules.gomoku.domain.entity.player.BlackStonePlayer
import run.moku.modules.gomoku.domain.entity.player.WhiteStonePlayer
import run.moku.modules.gomoku.domain.model.MokuPlayStatusModel
import run.moku.modules.gomoku.domain.model.MokuPlayingModel
import run.moku.modules.gomoku.domain.value.MokuTurn
import run.moku.modules.gomoku.domain.value.play.MokuPlayStone

@Service
class PlayCommandAdapter(
    private val repository: PlayConcurrentMap
) : PlayCommandPort {

    override fun start(blackStonePlayer: BlackStonePlayer, whiteStonePlayer: WhiteStonePlayer): MokuPlayingModel =
        MokuPlayingModel.start(MokuTurn.of(blackStonePlayer, whiteStonePlayer))
            .also {
                repository.save(it)
            }

    override fun play(boardId: BoardId, mokuPlayStone: MokuPlayStone): MokuPlayStatusModel {
        val model = repository.loadById(boardId)
        val result = model.playStone(mokuPlayStone)

        repository.save(model)

        return result
    }

    override fun record(boardId: BoardId, mokuPlayStone: MokuPlayStone) {
        TODO("Not yet implemented")
    }

    override fun removeBoard(boardId: BoardId) {
        TODO("Not yet implemented")
    }
}