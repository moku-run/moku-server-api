package run.moku.framework.security.jwt

import io.jsonwebtoken.Claims
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Component
import run.moku.framework.cache.CacheService
import java.time.Instant

@Component
class JwtValidator(
    val cacheService: CacheService,
    val jwtParser: JwtParser,
) {

    fun isValid(request: HttpServletRequest): Boolean {
        val token = request.cookies
            ?.firstOrNull { it.name == JwtValues.AUTHENTICATION_HEADER }
            ?.value
            ?.takeIf { it.isNotEmpty() }
            ?.let { jwtParser.parse(it) }

        return token != null
                && isNotRemoved(token)
                && isNotExpired(jwtParser.getClaims(token))
    }

    private fun isNotExpired(claims: Claims): Boolean = claims
        .expiration.toInstant()
        .isAfter(Instant.now())

    private fun isNotRemoved(token: String): Boolean {
        val value = cacheService.get(token)
        return value != null && value != "BLACK_LIST"
    }
}