package run.moku.modules.users.adapter.input.event

import org.springframework.context.event.EventListener
import run.moku.modules.users.application.usecase.command.signup.model.UserSignUpModel


abstract class UserEventSubscriberAdapter {

    @EventListener
    abstract fun handleUserSignUp(model: UserSignUpModel)
}