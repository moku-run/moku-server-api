package run.moku.framework.security.auth

import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import run.moku.framework.api.response.ApiResponseCode
import run.moku.modules.users.adapter.out.infrastructure.jpa.query.UserQueryRepository

@Component
class UserDetailsServiceAdapter(
    private val query: UserQueryRepository,
) : UserDetailsService {

    override fun loadUserByUsername(username: String?): AuthenticationDTO =
        username
            ?.let { query.findByLoginId(username) }
            ?.let {
                AuthenticationDTO(
                    role = it.role,
                    id = it.id!!,
                    loginId = it.loginId,
                    nickname = it.nickname,
                    encodedPassword = it.password,
                )
            }
            ?: throw BadCredentialsException(ApiResponseCode.INVALID_CREDENTIALS.message)
}