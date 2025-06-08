package run.moku.framework.security.jwt

import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.*

@Component
class JwtCreator(
    private val jwtProperties: JwtProperties,
) {
    fun create(claims: Map<String, *>): String {
        val now = Instant.now()

        return Jwts.builder()
            .claims(claims)
            .issuedAt(Date.from(now))
            .expiration(Date.from(now.plusSeconds(jwtProperties.expired)))
            .signWith(jwtProperties.getSecretKey())
            .issuer(jwtProperties.issuer)
            .compact()
    }
}