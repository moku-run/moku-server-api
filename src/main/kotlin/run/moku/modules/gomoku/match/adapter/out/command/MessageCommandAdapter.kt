package run.moku.modules.gomoku.match.adapter.out.command

import org.springframework.stereotype.Service
import run.moku.modules.gomoku.match.application.ports.out.command.MessageCommandPort
import run.moku.modules.gomoku.play.domain.entity.MokuPlayer

@Service
class MessageCommandAdapter(

) : MessageCommandPort {

    override fun <T> send(user: MokuPlayer, path: String, payload: T) {
        TODO("Not yet implemented")
    }
}