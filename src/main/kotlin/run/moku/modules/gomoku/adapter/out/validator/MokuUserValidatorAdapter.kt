package run.moku.modules.gomoku.adapter.out.validator

import org.springframework.stereotype.Service
import run.moku.framework.api.exception.ApiException
import run.moku.framework.api.response.ApiResponseCode
import run.moku.modules.gomoku.application.ports.out.validator.MokuUserValidator
import run.moku.modules.gomoku.domain.entity.user.MokuUser
import run.moku.modules.users.adapter.out.infrastructure.jpa.query.UserQueryRepository

@Service
class MokuUserValidatorAdapter(
    private val userQueryRepository: UserQueryRepository
) : MokuUserValidator {

    override fun valid(mokuUser: MokuUser) {
        userQueryRepository
            .findById(mokuUser.value)
            ?: throw ApiException(ApiResponseCode.NOT_FOUND_USER)
    }
}