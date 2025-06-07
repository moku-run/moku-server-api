package run.moku.framework.security.filter

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.logout.LogoutHandler
import org.springframework.stereotype.Component
import run.moku.framework.security.cookie.CookieService
import run.moku.framework.security.jwt.JwtService
import run.moku.framework.security.jwt.JwtValues

@Component
class JwtLogoutFilter(
    private val jwtService: JwtService,
    private val cookieService: CookieService,
) : LogoutHandler {

    override fun logout(request: HttpServletRequest?, response: HttpServletResponse?, authentication: Authentication?) {
        try {
            request
                ?.let { cookieService.getCookieValue(it, JwtValues.AUTHENTICATION_HEADER) }
                ?.also {
                    response?.let {
                        cookieService.removeCookie(it, JwtValues.AUTHENTICATION_HEADER)
                    }
                }
                ?.let { jwtService.remove(it) }
        } catch (ignore: Exception) {
        }
    }
}