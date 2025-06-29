package run.moku.modules.gomoku.match.application.ports.out.command

import run.moku.modules.gomoku.play.domain.entity.MokuPlayer

interface MessageCommandPort {
    fun <T> send(user: MokuPlayer, path: String, payload: T)
}