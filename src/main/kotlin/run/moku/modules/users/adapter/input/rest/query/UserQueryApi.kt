package run.moku.modules.users.adapter.input.rest.query

import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import run.moku.framework.api.response.ApiResponse
import run.moku.framework.security.auth.AuthenticationDTO
import run.moku.modules.users.adapter.input.rest.query.dto.UserQueryResponse
import run.moku.modules.users.adapter.input.rest.query.mapper.UserQueryMapper
import run.moku.modules.users.application.ports.input.query.FetchUserInput

@RestController
class UserQueryApi(
    private val fetchInput: FetchUserInput,
    private val mapper: UserQueryMapper,
) {

    @GetMapping("/api/users/details")
    fun getUsers(
        @AuthenticationPrincipal auth: AuthenticationDTO
    ): ResponseEntity<ApiResponse<UserQueryResponse.Details>> = fetchInput
        .fetchById(auth.id)
        .let { mapper.convert(it) }
        .let { ApiResponse.success(payload = it) }
}