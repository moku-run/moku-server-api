package run.moku.modules.users.domain.model

import run.moku.modules.users.domain.entity.UserLoginId
import run.moku.modules.users.domain.entity.UserNickname
import run.moku.modules.users.domain.value.UserPassword

class UserSignUpModel(
    val loginId: UserLoginId,
    val password: UserPassword,
    val nickname: UserNickname,
)