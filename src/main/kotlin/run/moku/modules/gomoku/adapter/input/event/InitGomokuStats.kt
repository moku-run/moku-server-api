package run.moku.modules.gomoku.adapter.input.event

import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import run.moku.modules.gomoku.application.ports.out.command.StatsCommandPort
import run.moku.modules.gomoku.domain.entity.user.MokuUser
import run.moku.modules.users.adapter.input.event.UserEventSubscriberAdapter
import run.moku.modules.users.domain.entity.UserId

@Component
class InitGomokuStats(
    private val statsCommandPort: StatsCommandPort
) : UserEventSubscriberAdapter() {

    @EventListener
    override fun handleUserSignUp(userId: UserId) {
        statsCommandPort.init(MokuUser(userId.value))
    }
}