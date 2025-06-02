package run.moku.modules.users.adapter.input.rest.command.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import run.moku.framework.api.exception.ApiException
import run.moku.framework.api.response.ApiResponseCode
import run.moku.modules.users.domain.entity.UserLoginId
import run.moku.modules.users.domain.entity.UserNickname
import run.moku.modules.users.domain.model.UserSignUpModel
import run.moku.modules.users.domain.value.UserPassword

interface UserCommandDto {
    data class SignUpDTO(
        @field:Size(max = UserLoginId.MAX_LENGTH, message = UserLoginId.LENGTH_MESSAGE)
        @field:Size(min = UserLoginId.MIN_LENGTH, message = UserLoginId.LENGTH_MESSAGE)
        @field:NotEmpty(message = UserLoginId.REQUIRED_MESSAGE)
        @field:Pattern(regexp = UserLoginId.PATTERN_STRING, message = UserLoginId.PATTERN_MESSAGE)
        val loginId: String,

        @field:NotEmpty(message = UserNickname.REQUIRED_MESSAGE)
        @field:Size(
            min = UserNickname.MIN_LENGTH,
            max = UserNickname.MAX_LENGTH,
            message = UserNickname.LENGTH_VALID_MESSAGE
        )
        val nickname: String,

        @field:NotEmpty(message = UserPassword.REQUIRED_MESSAGE)
        @field:Pattern(regexp = UserPassword.PATTERN_STRING, message = UserPassword.VALID_PATTERN_MESSAGE)
        val password: String,

        @field:NotEmpty(message = UserPassword.REQUIRED_MESSAGE)
        @field:Pattern(regexp = UserPassword.PATTERN_STRING, message = UserPassword.VALID_PATTERN_MESSAGE)
        val passwordConfirm: String,
    ) {
        init {
            require(password == passwordConfirm) { throw ApiException(ApiResponseCode.MISMATCH_PASSWORD_AND_PASSWORD_CONFIRM) }
        }

        fun convert() = UserSignUpModel(
            UserLoginId.of(loginId),
            UserPassword.of(password),
            UserNickname.of(nickname),
        )
    }
}