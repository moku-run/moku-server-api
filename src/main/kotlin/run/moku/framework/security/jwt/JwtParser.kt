package run.moku.framework.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Component

@Component
class JwtParser(
    val jwtProperties: JwtProperties
) {

    fun getClaims(token: String): Claims =
        Jwts.parser()
            .verifyWith(jwtProperties.getSecretKey())
            .build()
            .parseSignedClaims(token)
            .payload

    fun getUsername(token: String): String =
        getClaims(token).get(JwtValues.USERNAME_KEY, JwtValues.USERNAME_TYPE)
}