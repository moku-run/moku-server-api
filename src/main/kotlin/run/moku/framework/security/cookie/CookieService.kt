package run.moku.framework.security.cookie

import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import org.springframework.boot.web.server.Cookie.SameSite
import org.springframework.stereotype.Service

@Service
class CookieService {

    fun getCookieValue(request: HttpServletRequest, key: String): String? = request
        .cookies
        ?.firstOrNull { it.name == key }
        ?.value

    fun createCookie(key: String, value: String): Cookie = Cookie(key, value)
        .apply {
            path = "/"
            maxAge = 60 * 60 * 1
            secure = true
            isHttpOnly = true
        }

    fun createCookieAsString(
        key: String,
        value: String,
        path: String? = "/",
        maxAge: Int? = 60 * 60 * 1,
        isHttpOnly: Boolean? = true,
        secure: Boolean? = true,
        strict: SameSite? = SameSite.NONE,
    ): String =
        "${key}=${value}; Path=${path}; Max-Age=${maxAge}; HttpOnly=${isHttpOnly}; Secure=${secure}; SameSite=${strict}"

    fun removeCookie() {
    }
}