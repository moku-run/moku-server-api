package run.moku.framework.socket.interceptor

import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.stereotype.Component
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.server.HandshakeInterceptor
import run.moku.framework.security.auth.UserDetailsServiceAdapter
import run.moku.framework.security.jwt.JwtService
import run.moku.framework.security.jwt.JwtValues

@Component
class JwtHandshakeInterceptor(
    private val jwtService: JwtService,
    private val userDetailsServiceAdapter: UserDetailsServiceAdapter
) : HandshakeInterceptor {

    override fun beforeHandshake(
        request: ServerHttpRequest,
        response: ServerHttpResponse,
        wsHandler: WebSocketHandler,
        attributes: MutableMap<String, Any>
    ): Boolean {
        request
            .headers["cookie"]
            ?.let { cookie ->
                cookie
                    .flatMap { it.split(";") }
                    .map { it.trim() }
                    .firstOrNull { it.startsWith(JwtValues.AUTHENTICATION_HEADER) }
                    ?.removePrefix(JwtValues.AUTHENTICATION_HEADER + "=")
                    ?.takeIf { jwtService.validToken(it) }
                    ?.let { jwtService.getUsername(it) }
                    ?.let {
                        attributes[JwtValues.AUTHENTICATION_HEADER] = userDetailsServiceAdapter.loadUserByUsername(it)
                    }
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