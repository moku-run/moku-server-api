package run.moku.modules.gomoku.play.application.ports.input

import org.springframework.stereotype.Service
import run.moku.modules.gomoku.play.application.ports.out.command.PlayCommandPort
import run.moku.modules.gomoku.play.application.ports.out.query.PlayQueryPort
import run.moku.modules.gomoku.play.application.ports.out.validator.PlayValidator
import run.moku.modules.gomoku.play.application.usecase.command.PlayUsecase
import run.moku.modules.gomoku.play.application.usecase.command.checkTurn
import run.moku.modules.gomoku.play.application.usecase.command.playStone
import run.moku.modules.gomoku.board.domain.entity.BoardId
import run.moku.modules.gomoku.play.domain.model.MokuPlayStatusModel
import run.moku.modules.gomoku.play.domain.value.MokuPlayStone

@Service
class PlayInput(
    private val playValidator: PlayValidator,
    private val playCommandPort: PlayCommandPort,
    private val playQueryPort: PlayQueryPort,
) {

    fun play(boardId: BoardId, playStone: MokuPlayStone): MokuPlayStatusModel =
        PlayUsecase.execute(boardId, playStone) {
            checkTurn(playValidator::checkTurn)
            playStone(playCommandPort::play)
        }

    fun removeBoard(boardId: BoardId) {
        playCommandPort.removeBoard(boardId)
    }

//    fun getCurrentPlayer(boardId: BoardId) {
//        val model = list[boardId] ?: throw IllegalArgumentException("INVALID BOARD ID")
//    }
//
//    fun getBoardModel(id: BoardId) = list[id]
}