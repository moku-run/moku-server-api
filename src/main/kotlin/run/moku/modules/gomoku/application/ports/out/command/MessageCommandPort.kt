package run.moku.modules.gomoku.application.ports.out.command

import run.moku.modules.gomoku.domain.entity.player.MokuPlayer

interface MessageCommandPort {
    fun <T> send(user: MokuPlayer, path: String, payload: T)
}