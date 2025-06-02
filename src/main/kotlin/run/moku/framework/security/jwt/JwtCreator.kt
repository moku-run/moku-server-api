package run.moku.framework.security.jwt

import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.*

@Component
class JwtCreator(
    private val jwtProperties: JwtProperties,
) {

    fun create(username: String): String {
        val now = Instant.now()

        return Jwts.builder()
            .claims(
                mapOf(
                    JwtValues.USERNAME_KEY to username
                ),
            )
            .expiration(Date.from(now.plusSeconds(jwtProperties.expired)))
            .issuedAt(Date.from(now))
            .issuer(jwtProperties.issuer)
            .signWith(jwtProperties.getSecretKey())
            .compact()
    }
}