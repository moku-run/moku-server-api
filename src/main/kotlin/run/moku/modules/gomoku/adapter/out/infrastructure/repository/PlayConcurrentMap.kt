package run.moku.modules.gomoku.adapter.out.infrastructure.repository

import org.springframework.stereotype.Service
import run.moku.modules.gomoku.domain.entity.board.BoardId
import run.moku.modules.gomoku.domain.model.MokuPlayingModel
import java.util.concurrent.ConcurrentHashMap

@Service
class PlayConcurrentMap {

    fun save(model: MokuPlayingModel) {
        LIST[model.boardId] = model
    }

    fun loadById(boardId: BoardId) = LIST[boardId] ?: throw IllegalArgumentException("!!!!!!!")

    fun remove(boardId: BoardId) {
        LIST.remove(boardId)
    }

    companion object {
        private val LIST: MutableMap<BoardId, MokuPlayingModel> = ConcurrentHashMap()
    }
}