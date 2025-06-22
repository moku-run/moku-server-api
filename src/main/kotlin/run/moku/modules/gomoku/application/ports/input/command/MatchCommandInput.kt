package run.moku.modules.gomoku.application.ports.input.command

import run.moku.modules.gomoku.domain.entity.player.MokuPlayer
import run.moku.modules.gomoku.domain.model.MokuPlayingModel

interface MatchCommandInput {
    fun addQueue(player: MokuPlayer)
    fun start(): MokuPlayingModel
    fun <T> sendToUser(player: MokuPlayer, path: String, payload: T)
}