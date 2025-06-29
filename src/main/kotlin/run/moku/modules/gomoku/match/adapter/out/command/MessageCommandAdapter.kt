package run.moku.modules.gomoku.match.adapter.out.command

import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service
import run.moku.modules.gomoku.match.application.ports.out.command.MessageCommandPort
import run.moku.modules.gomoku.play.domain.entity.MokuPlayer

@Service
class MessageCommandAdapter(
    private val messagingTemplate: SimpMessagingTemplate,
) : MessageCommandPort {

    override fun send(user: MokuPlayer, path: String, payload: Any) {
        messagingTemplate.convertAndSendToUser(user.id, path, payload)
    }
}