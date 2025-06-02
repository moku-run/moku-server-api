package run.moku.modules.users.adapter.out.validator

import org.springframework.stereotype.Service
import run.moku.framework.adapter.validator.throwIf
import run.moku.framework.api.response.ApiResponseCode
import run.moku.modules.users.application.ports.out.query.UserQueryPort
import run.moku.modules.users.application.ports.out.validator.UserValidator
import run.moku.modules.users.domain.entity.UserLoginId
import run.moku.modules.users.domain.entity.UserNickname

@Service
class UserValidatorAdapter(
    private val userQueryPort: UserQueryPort
) : UserValidator {

    override fun checkDuplicateLoginId(target: UserLoginId) {
        throwIf(userQueryPort.existsLoginId(target), ApiResponseCode.DUPLICATE_LOGIN_ID)
    }

    override fun checkDuplicateNickname(target: UserNickname) {
        throwIf(userQueryPort.existsNickname(target), ApiResponseCode.DUPLICATE_NICKNAME)
    }
}