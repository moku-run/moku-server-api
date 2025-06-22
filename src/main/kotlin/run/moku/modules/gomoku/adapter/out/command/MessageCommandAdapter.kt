package run.moku.modules.gomoku.adapter.out.command

import org.springframework.stereotype.Service
import run.moku.modules.gomoku.application.ports.out.command.MessageCommandPort
import run.moku.modules.gomoku.domain.entity.player.MokuPlayer

@Service
class MessageCommandAdapter(

) : MessageCommandPort {

    override fun <T> send(user: MokuPlayer, path: String, payload: T) {
        TODO("Not yet implemented")
    }
}