package run.moku.framework.socket.interceptor

import org.springframework.messaging.Message
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.messaging.support.ChannelInterceptor
import org.springframework.messaging.support.MessageHeaderAccessor
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import run.moku.framework.security.auth.AuthenticationDTO
import run.moku.framework.security.jwt.JwtService
import java.util.UUID

@Component
class JwtChannelInterceptor(
    private val jwtService: JwtService
) : ChannelInterceptor {
    override fun preSend(message: Message<*>, channel: MessageChannel): Message<*>? {
        val accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor::class.java)

        if (accessor != null && accessor.user == null) {
            val sessionAttr = accessor.sessionAttributes?.get(jwtService.authorizationHeader()) as? AuthenticationDTO

            if (sessionAttr != null) {
                val authToken = UsernamePasswordAuthenticationToken(sessionAttr, null, sessionAttr?.authorities)
                accessor.user = authToken
                SecurityContextHolder.getContext().authentication = authToken
            } else {
                val authToken = UsernamePasswordAuthenticationToken(UUID.randomUUID(), null)
                accessor.user = authToken
                SecurityContextHolder.getContext().authentication = authToken
            }
        }

        return message
    }
}