package run.moku.modules.users.application.ports.out.event

import run.moku.modules.users.application.usecase.command.signup.model.UserSignUpModel

interface UserEventPublisher {
    fun signUpEvent(model: UserSignUpModel)
}