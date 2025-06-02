package run.moku.modules.gomoku.application.ports.out.command

interface MessageCommandPort {
    fun <T> sendToUser(user: String, path: String, payload: T)
}