package run.moku.modules.users.application.usecase.command.signup.model

import run.moku.modules.users.domain.entity.UserId
import run.moku.modules.users.domain.entity.UserLoginId
import run.moku.modules.users.domain.entity.UserNickname
import run.moku.modules.users.domain.value.UserPassword

data class UserSignUpModel(
    val loginId: UserLoginId,
    val password: UserPassword,
    val nickname: UserNickname,
) {
    var userId: UserId? = null
}