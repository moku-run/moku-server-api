package run.moku.modules.users.application.ports.out.validator

import run.moku.modules.users.domain.entity.UserLoginId
import run.moku.modules.users.domain.entity.UserNickname

interface UserValidator {
    fun checkDuplicateNickname(target: UserNickname)
    fun checkDuplicateLoginId(target: UserLoginId)
}