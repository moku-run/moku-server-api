package run.moku.framework.security.jwt

import io.jsonwebtoken.Claims
import org.springframework.stereotype.Component
import run.moku.framework.cache.CacheService
import java.time.Instant

@Component
class JwtValidator(
    private val cacheService: CacheService,
    private val parser: JwtParser,
) {

    fun validate(token: String?): Boolean =
        token != null
                && isNotRemoved(token)
                && isNotExpired(parser.getClaims(token))

    private fun isNotExpired(claims: Claims): Boolean =
        claims
            .expiration.toInstant()
            .isAfter(Instant.now())

    private fun isNotRemoved(token: String): Boolean =
        cacheService.get(token) != JwtValues.BLACK_LIST
}