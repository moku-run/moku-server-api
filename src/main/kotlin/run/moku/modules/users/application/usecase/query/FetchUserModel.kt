package run.moku.modules.users.application.usecase.query

import run.moku.modules.users.domain.entity.UserId
import run.moku.modules.users.domain.entity.UserLoginId
import run.moku.modules.users.domain.entity.UserNickname
import run.moku.modules.users.domain.value.UserRole

data class FetchUserModel(
    val userId: UserId,
    val userLoginId: UserLoginId,
    val userNickname: UserNickname,
    val role: UserRole
)