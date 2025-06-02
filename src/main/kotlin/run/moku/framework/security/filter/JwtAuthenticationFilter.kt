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
            .let { cookieService.getCookieValue(it, jwtService.authorizationHeader()) }
            .takeIf { jwtService.validToken(it) }
            ?.let { jwtService.getUsername(it) }
            ?.let {
                val authenticationToken = userDetailsServiceAdapter.loadUserByUsername(it)
                saveAuthentication(
                    UsernamePasswordAuthenticationToken(authenticationToken, null)
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