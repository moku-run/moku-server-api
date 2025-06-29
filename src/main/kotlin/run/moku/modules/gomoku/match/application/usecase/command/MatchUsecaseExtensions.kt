package run.moku.modules.gomoku.match.application.usecase.command

import run.moku.modules.gomoku.board.domain.entity.BoardId
import run.moku.modules.gomoku.play.domain.entity.MokuPlayer

fun MatchUsecase.addQueue(add: (MokuPlayer) -> (Unit)) = add(player)

fun MatchUsecase.join(add: (MokuPlayer) -> BoardId) = add(player)