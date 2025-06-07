package run.moku.modules.users.adapter.out.query

import org.springframework.stereotype.Service
import run.moku.framework.security.auth.AuthenticationDTO
import run.moku.modules.users.adapter.out.infrastructure.jpa.query.UserQueryRepository
import run.moku.modules.users.application.ports.input.query.AuthenticationQueryInput

@Service
class AuthenticationQueryAdapter(
    private val query: UserQueryRepository
) : AuthenticationQueryInput {

    override fun loadUserDetails(username: String): AuthenticationDTO? = query
        .findByLoginId(username)
        ?.let {
            AuthenticationDTO(
                role = it.role,
                id = it.id!!,
                loginId = it.loginId,
                nickname = it.nickname,
                encodedPassword = it.password,
            )
        }
}