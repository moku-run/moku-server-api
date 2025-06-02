package run.moku.modules.users.adapter.input.rest.query

import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import run.moku.framework.api.response.ApiResponse
import run.moku.framework.security.auth.AuthenticationDTO
import run.moku.modules.users.adapter.input.rest.query.dto.UserQueryResponse

@RestController
class UserQueryApi {

    @GetMapping("/api/users")
    fun getUsers(
        @AuthenticationPrincipal auth: AuthenticationDTO
    ): ResponseEntity<ApiResponse<UserQueryResponse.Get>> {
        return ApiResponse.success(
            payload = UserQueryResponse.Get(
                nickname = auth.nickname,
                loginId = auth.loginId,
            )
        )
    }
}