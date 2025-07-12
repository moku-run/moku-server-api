package run.moku.modules.gomoku.match.application.ports.input.command

import run.moku.modules.gomoku.play.domain.entity.MokuPlayer
import run.moku.modules.gomoku.play.domain.model.MokuPlayingModel

interface MatchCommandInputPort {
    fun addQueue(player: MokuPlayer)
    fun start(): MokuPlayingModel
}