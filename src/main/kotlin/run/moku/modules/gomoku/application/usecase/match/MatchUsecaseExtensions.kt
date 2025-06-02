package run.moku.modules.gomoku.application.usecase.match

import run.moku.modules.gomoku.domain.entity.board.BoardId
import run.moku.modules.gomoku.domain.entity.player.MokuPlayer

fun MatchUsecase.addQueue(add: (MokuPlayer) -> (Unit)) = add(player)

fun MatchUsecase.join(add: (MokuPlayer) -> BoardId) = add(player)