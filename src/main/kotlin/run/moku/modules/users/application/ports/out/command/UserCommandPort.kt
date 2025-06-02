package run.moku.modules.users.application.ports.out.command

import run.moku.modules.users.domain.model.UserSignUpModel

interface UserCommandPort {
    fun registry(user: UserSignUpModel)
}