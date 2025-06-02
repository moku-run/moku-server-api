package run.moku.modules.gomoku.adapter.out.validator

import org.springframework.stereotype.Service
import run.moku.framework.adapter.validator.throwIf
import run.moku.framework.api.exception.ApiException
import run.moku.framework.api.response.ApiResponseCode
import run.moku.modules.gomoku.adapter.out.infrastructure.repository.PlayConcurrentMap
import run.moku.modules.gomoku.application.ports.out.validator.PlayValidator
import run.moku.modules.gomoku.domain.entity.board.BoardId
import run.moku.modules.gomoku.domain.value.play.MokuPlayStone

@Service
class PlayValidatorAdapter(
    private val repository: PlayConcurrentMap
) : PlayValidator {

    override fun checkTurn(boardId: BoardId, mokuPlayStone: MokuPlayStone) {
        val model = repository.loadById(boardId)

        throwIf(model.getCurrentPlayer() != mokuPlayStone.mokuPlayer, ApiException(ApiResponseCode.PLAY_INVALID_TURN))
    }
}