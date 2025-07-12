package run.moku.modules.gomoku.play.adapter.out.query

import org.springframework.stereotype.Service
import run.moku.modules.gomoku.play.adapter.out.infrastructure.repository.PlayConcurrentMap
import run.moku.modules.gomoku.play.application.ports.out.query.PlayQueryPort
import run.moku.modules.gomoku.board.domain.entity.BoardId
import run.moku.modules.gomoku.play.domain.model.MokuPlayStatusModel
import run.moku.modules.gomoku.play.domain.model.MokuPlayingModel

@Service
class PlayQueryAdapter(
    private val repository: PlayConcurrentMap
) : PlayQueryPort {

    override fun getModel(boardId: BoardId): MokuPlayingModel =
        repository.loadById(boardId)

    override fun result(boardId: BoardId): MokuPlayStatusModel {
        TODO("Not yet implemented")
    }
}