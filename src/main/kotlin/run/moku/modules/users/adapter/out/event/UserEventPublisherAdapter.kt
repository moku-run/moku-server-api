package run.moku.modules.users.adapter.out.event

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import run.moku.modules.users.application.ports.out.event.UserEventPublisher
import run.moku.modules.users.application.usecase.command.signup.model.UserSignUpModel

@Service
class UserEventPublisherAdapter(
    private val applicationEventPublisher: ApplicationEventPublisher
) : UserEventPublisher {

    override fun signUpEvent(model: UserSignUpModel) =
        applicationEventPublisher.publishEvent(model)

}