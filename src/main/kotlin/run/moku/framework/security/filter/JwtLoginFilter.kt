package run.moku.framework.security.filter

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.annotation.PostConstruct
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component
import run.moku.framework.adapter.validator.throwUnless
import run.moku.framework.api.response.ApiResponseCode
import run.moku.framework.api.response.ApiResponseService
import run.moku.framework.security.auth.AuthenticationDTO
import run.moku.framework.security.auth.LoginDTO
import run.moku.framework.security.auth.UserDetailsServiceAdapter
import run.moku.framework.security.cookie.CookieService
import run.moku.framework.security.jwt.JwtService

@Component
class JwtLoginFilter(
    private val userDetailsServiceAdapter: UserDetailsServiceAdapter,
    private val apiResponseService: ApiResponseService,
    val objectMapper: ObjectMapper,
    val passwordEncoder: PasswordEncoder,
    val cookieService: CookieService,
    val jwtService: JwtService,

    authenticationManager: AuthenticationManager
) : UsernamePasswordAuthenticationFilter(authenticationManager) {

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        val loginDTO = getBody(request)
        val user = userDetailsServiceAdapter.loadUserByUsername(loginDTO.loginId)
        checkCredentials(loginDTO, user)

        return UsernamePasswordAuthenticationToken(user, user.authorities)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {

        println("##########################")
        val user = authResult?.principal as? AuthenticationDTO
            ?: throw BadCredentialsException(ApiResponseCode.INVALID_CREDENTIALS.message)

        val token = jwtService.createToken(user.loginId)
        val cookie = cookieService.createCookieAsString(jwtService.authorizationHeader(), token)

        response?.addHeader("Set-Cookie", cookie)

        apiResponseService.writeResponse<Unit>(
            response = response,
            success = true,
            apiResponseCode = ApiResponseCode.OK,
        )

        println("##################################$cookie")
    }

    override fun unsuccessfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        failed: AuthenticationException?
    ) {
        apiResponseService.writeResponse<Unit>(
            response = response,
            success = false,
            apiResponseCode = ApiResponseCode.INVALID_CREDENTIALS,
        )
    }

    private fun getBody(request: HttpServletRequest?): LoginDTO =
        objectMapper.readValue(request?.inputStream, LoginDTO::class.java)

    private fun checkCredentials(target: LoginDTO, user: AuthenticationDTO) {
        throwUnless(
            passwordEncoder.matches(target.password, user.encodedPassword),
            BadCredentialsException(ApiResponseCode.INVALID_CREDENTIALS.message)
        )
    }

    @PostConstruct
    fun initFilterUrl() {
        setFilterProcessesUrl("/api/login")
    }
}