package run.moku.modules.users.adapter.input.rest.command

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import run.moku.framework.api.response.ApiResponse
import run.moku.modules.users.adapter.input.rest.command.dto.UserCommandDto
import run.moku.modules.users.application.ports.input.command.SignUpInput

@RestController
class UserSignUpApi(
    private val signUpInPort: SignUpInput
) {

    @PostMapping("/api/users")
    fun getSignUp(
        @Valid @RequestBody request: UserCommandDto.SignUpDTO
    ): ResponseEntity<ApiResponse<String>> {
        signUpInPort.perform(request.convert())

        return ApiResponse.successCreated("회원가입에 성공했습니다.")
    }
}