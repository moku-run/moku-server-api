package run.moku.modules.users.application.ports.out.command

import run.moku.modules.users.application.usecase.command.signup.model.UserSignUpModel
import run.moku.modules.users.domain.entity.UserId

interface UserCommandPort {
    fun registry(user: UserSignUpModel): UserId
}