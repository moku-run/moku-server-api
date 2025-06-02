package run.moku.framework.security.auth

import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import run.moku.framework.api.response.ApiResponseCode
import run.moku.modules.users.adapter.internal.security.AuthenticationQueryPort

@Component
class UserDetailsServiceAdapter(
    private val authenticationPort: AuthenticationQueryPort
) : UserDetailsService {

    override fun loadUserByUsername(username: String?): AuthenticationDTO = username
        ?.let { authenticationPort.loadUserDetails(username) }
        ?: throw BadCredentialsException(ApiResponseCode.INVALID_CREDENTIALS.message)
}