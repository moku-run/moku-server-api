package run.moku.modules.gomoku.stats.adapter.input.event

import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import run.moku.modules.gomoku.stats.application.ports.out.command.StatsCommandPort
import run.moku.modules.gomoku.player.domain.entity.MokuPlayer
import run.moku.modules.users.adapter.input.event.UserEventSubscriberAdapter
import run.moku.modules.users.domain.entity.UserId

@Component
class InitGomokuStats(
    private val statsCommandPort: StatsCommandPort
) : UserEventSubscriberAdapter() {

    @EventListener
    override fun handleUserSignUp(userId: UserId) {
        statsCommandPort.init(MokuPlayer(userId.value))
    }
}