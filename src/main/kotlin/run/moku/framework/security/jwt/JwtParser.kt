package run.moku.framework.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Component

@Component
class JwtParser(
    val jwtProperties: JwtProperties
) {

    fun parse(value: String?): String? {
        if (value != null && value.startsWith(JwtValues.TOKEN_PREFIX)) {
            return value.substring(JwtValues.TOKEN_PREFIX.length);
        }

        return value
    }

    fun getClaims(token: String): Claims = Jwts.parser()
        .verifyWith(jwtProperties.getSecretKey())
        .build()
        .parseSignedClaims(token)
        .payload
}