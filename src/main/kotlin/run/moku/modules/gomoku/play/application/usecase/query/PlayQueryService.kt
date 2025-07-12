package run.moku.modules.gomoku.play.application.usecase.query

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import run.moku.modules.gomoku.play.application.ports.input.query.PlayQueryInput
import run.moku.modules.gomoku.play.application.ports.out.query.PlayQueryPort
import run.moku.modules.gomoku.board.domain.entity.BoardId

@Service
@Transactional(readOnly = true)
class PlayQueryService(
    private val playQueryPort: PlayQueryPort
) : PlayQueryInput {

    override fun getDetails(boardId: BoardId) {
        playQueryPort.getModel(boardId)
    }
}