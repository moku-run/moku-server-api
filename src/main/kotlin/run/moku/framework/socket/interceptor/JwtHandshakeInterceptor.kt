package run.moku.framework.socket.interceptor

import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.stereotype.Component
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.server.HandshakeInterceptor
import run.moku.framework.security.auth.UserDetailsServiceAdapter
import run.moku.framework.security.cookie.CookieService
import run.moku.framework.security.jwt.JwtService

@Component
class JwtHandshakeInterceptor(
    private val jwtService: JwtService,
    private val cookieService: CookieService,
    private val userDetailsServiceAdapter: UserDetailsServiceAdapter
) : HandshakeInterceptor {

    override fun beforeHandshake(
        request: ServerHttpRequest,
        response: ServerHttpResponse,
        wsHandler: WebSocketHandler,
        attributes: MutableMap<String, Any>
    ): Boolean {
        val cookies = request.headers["cookie"] ?: return true

        val token = cookies.flatMap { it.split(";") }
            .map { it.trim() }
            .firstOrNull { it.startsWith(jwtService.authorizationHeader()) }
            ?.removePrefix(jwtService.authorizationHeader() + "=")

        if (token != null && jwtService.validToken(token)) {
            val username = jwtService.getUsername(token)
            attributes[jwtService.authorizationHeader()] = userDetailsServiceAdapter.loadUserByUsername(username)
        }

        return true
    }

    override fun afterHandshake(
        request: ServerHttpRequest,
        response: ServerHttpResponse,
        wsHandler: WebSocketHandler,
        exception: Exception?
    ) {
    }
}