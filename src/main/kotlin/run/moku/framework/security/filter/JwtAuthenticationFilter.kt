package run.moku.framework.security.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import run.moku.framework.security.auth.UserDetailsServiceAdapter
import run.moku.framework.security.cookie.CookieService
import run.moku.framework.security.jwt.JwtService
import run.moku.framework.security.jwt.JwtValues

@Component
class JwtAuthenticationFilter(
    private val jwtService: JwtService,
    private val cookieService: CookieService,
    private val userDetailsServiceAdapter: UserDetailsServiceAdapter,
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        request
            .let { cookieService.getCookieValue(it, JwtValues.AUTHENTICATION_HEADER) }
            ?.takeIf { jwtService.validToken(it) }
            ?.let { jwtService.getUsername(it) }
            ?.let { userDetailsServiceAdapter.loadUserByUsername(it) }
            ?.let {
                saveAuthentication(
                    UsernamePasswordAuthenticationToken(it, null, it.authorities)
                )
            }

        filterChain.doFilter(request, response)
    }

    private fun saveAuthentication(token: UsernamePasswordAuthenticationToken) {
        SecurityContextHolder.setContext(
            SecurityContextHolder.createEmptyContext()
                .apply {
                    authentication = token
                }
        )
    }
}