package run.moku.framework.security.cookie

import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.boot.web.server.Cookie.SameSite
import org.springframework.http.ResponseCookie
import org.springframework.stereotype.Service

@Service
class CookieService {

    fun getCookieValue(request: HttpServletRequest, key: String): String? =
        request
            .cookies
            ?.firstOrNull {
                it.name == key
            }
            ?.value

    fun createCookie(key: String, value: String): Cookie =
        Cookie(key, value)
            .apply {
                path = "/"
                maxAge = 60 * 60 * 1
                secure = true
                isHttpOnly = true
            }

    fun createCookieAsString(
        key: String,
        value: String,
        path: String = "/",
        maxAge: Long = 60 * 60 * 1,
        isHttpOnly: Boolean = true,
        isSecure: Boolean = true,
        strict: SameSite = SameSite.NONE,
    ): String =
        ResponseCookie
            .from(key, value)
            .httpOnly(isHttpOnly)
            .secure(isSecure)
            .sameSite(strict.attributeValue())
            .path(path)
            .maxAge(maxAge)
            .build()
            .toString()

    fun removeCookie(response: HttpServletResponse, key: String) {
        val cookie = Cookie(key, null).apply {
            path = "/"
            maxAge = 0
            isHttpOnly = true
            secure = true
        }

        response.addCookie(cookie)
    }
}