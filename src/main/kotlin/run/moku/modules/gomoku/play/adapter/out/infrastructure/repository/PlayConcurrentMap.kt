package run.moku.modules.gomoku.play.adapter.out.infrastructure.repository

import org.springframework.stereotype.Service
import run.moku.framework.api.exception.ApiException
import run.moku.framework.api.response.ApiResponseCode
import run.moku.modules.gomoku.board.domain.entity.BoardId
import run.moku.modules.gomoku.play.domain.model.MokuPlayingModel
import java.util.concurrent.ConcurrentHashMap

@Service
class PlayConcurrentMap {

    fun save(model: MokuPlayingModel) {
        LIST[model.boardId] = model
    }

    fun loadById(boardId: BoardId) = LIST[boardId] ?: throw ApiException(ApiResponseCode.PLAY_NOT_FOUND)

    fun remove(boardId: BoardId) {
        LIST.remove(boardId)
    }

    companion object {
        private val LIST: MutableMap<BoardId, MokuPlayingModel> = ConcurrentHashMap()
    }
}