package run.moku.modules.users.adapter.out.event

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import run.moku.modules.users.application.ports.out.event.UserEventPublisher
import run.moku.modules.users.domain.entity.UserId

@Service
class UserEventPublisherAdapter(
    private val applicationEventPublisher: ApplicationEventPublisher
) : UserEventPublisher {

    override fun signUpEvent(userId: UserId) =
        applicationEventPublisher.publishEvent(userId)

}