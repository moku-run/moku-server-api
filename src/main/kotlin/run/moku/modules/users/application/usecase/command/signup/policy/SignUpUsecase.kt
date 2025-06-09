package run.moku.modules.users.application.usecase.command.signup.policy

import run.moku.modules.users.application.usecase.command.signup.model.UserSignUpModel
import run.moku.modules.users.domain.entity.UserId

class SignUpUsecase private constructor(
    val model: UserSignUpModel
) {

    var userId: UserId? = null

    companion object {
        fun execute(model: UserSignUpModel, block: SignUpUsecase.() -> Unit) =
            block(SignUpUsecase(model))
    }
}