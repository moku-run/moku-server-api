package run.moku.modules.users.adapter.internal.security

import org.springframework.stereotype.Service
import run.moku.framework.security.auth.AuthenticationDTO
import run.moku.modules.users.adapter.out.infrastructure.jpa.query.UserQueryRepository

@Service
class AuthenticationQueryAdapter(
    private val query: UserQueryRepository
) : AuthenticationQueryPort {

    override fun loadUserDetails(username: String): AuthenticationDTO? = query
        .findByLoginId(username)
        ?.let {
            AuthenticationDTO(
                role = it.role,
                id = it.id,
                loginId = it.loginId,
                nickname = it.nickname,
                encodedPassword = it.password,
            )
        }
}