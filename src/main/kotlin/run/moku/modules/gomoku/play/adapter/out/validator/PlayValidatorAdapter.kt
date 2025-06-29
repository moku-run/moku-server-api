package run.moku.modules.gomoku.play.adapter.out.validator

import org.springframework.stereotype.Service
import run.moku.framework.adapter.validator.throwIf
import run.moku.framework.api.exception.ApiException
import run.moku.framework.api.response.ApiResponseCode
import run.moku.modules.gomoku.play.adapter.out.infrastructure.repository.PlayConcurrentMap
import run.moku.modules.gomoku.play.application.ports.out.validator.PlayValidator
import run.moku.modules.gomoku.board.domain.entity.BoardId
import run.moku.modules.gomoku.play.domain.value.MokuPlayStone

@Service
class PlayValidatorAdapter(
    private val repository: PlayConcurrentMap
) : PlayValidator {

    override fun checkTurn(boardId: BoardId, mokuPlayStone: MokuPlayStone) {
        val model = repository.loadById(boardId)

        throwIf(model.getCurrentPlayer() != mokuPlayStone.mokuPlayer, ApiException(ApiResponseCode.PLAY_INVALID_TURN))
    }
}