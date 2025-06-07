package run.moku.modules.users.application.ports.input.command

import run.moku.modules.users.application.usecase.command.signup.model.UserSignUpModel

interface SignUpInput {
    fun perform(model: UserSignUpModel)
}