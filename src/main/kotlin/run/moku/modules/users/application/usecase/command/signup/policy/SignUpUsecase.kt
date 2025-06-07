package run.moku.modules.users.application.usecase.command.signup.policy

import run.moku.modules.users.application.usecase.command.signup.model.UserSignUpModel

class SignUpUsecase private constructor(val model: UserSignUpModel) {

    companion object {
        fun execute(model: UserSignUpModel, block: SignUpUsecase.() -> Unit) =
            block(SignUpUsecase(model))
    }
}