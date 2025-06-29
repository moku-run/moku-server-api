package run.moku.modules.gomoku.match.adapter.input.web.event

import org.springframework.context.event.EventListener
import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import org.springframework.stereotype.Service
import org.springframework.web.socket.messaging.SessionDisconnectEvent
import run.moku.modules.gomoku.match.application.usecase.command.remove.MatchRemoveService
import run.moku.modules.gomoku.play.domain.entity.MokuPlayer

@Service
class MokuMatchingRemoveEventListener(
    private val matchRemoveService: MatchRemoveService
) {

    @EventListener
    fun handle(event: SessionDisconnectEvent) {
        val headers = SimpMessageHeaderAccessor.wrap(event.message)
        val principal = headers.user

        principal?.let {
            matchRemoveService.remove(MokuPlayer(it.name))
        }
    }
}