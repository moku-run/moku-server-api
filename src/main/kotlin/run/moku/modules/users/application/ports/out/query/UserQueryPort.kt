package run.moku.modules.users.application.ports.out.query

import run.moku.modules.users.domain.entity.UserLoginId
import run.moku.modules.users.domain.entity.UserNickname

interface UserQueryPort {
    fun existsLoginId(loginId: UserLoginId): Boolean
    fun existsNickname(nickname: UserNickname): Boolean
}