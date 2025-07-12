package run.moku.modules.users.adapter.input.event

import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service
import run.moku.modules.users.domain.entity.UserId

@Service
abstract class UserEventSubscriberAdapter {

    @EventListener
    abstract fun handleUserSignUp(userId: UserId)
}