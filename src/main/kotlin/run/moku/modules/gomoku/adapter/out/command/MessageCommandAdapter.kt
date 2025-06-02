package run.moku.modules.gomoku.adapter.out.command

import org.springframework.stereotype.Service
import run.moku.modules.gomoku.application.ports.out.command.MessageCommandPort

@Service
class MessageCommandAdapter(

) : MessageCommandPort {

    override fun <T> sendToUser(user: String, path: String, payload: T) {
        TODO("Not yet implemented")
    }
}