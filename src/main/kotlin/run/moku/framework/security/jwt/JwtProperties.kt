package run.moku.framework.security.jwt

import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import javax.crypto.SecretKey

@Component
class JwtProperties(
    @Value("\${app.jwt.secret_key}")
    private val secretKey: String,

    @Value("\${app.jwt.issuer}")
    val issuer: String,

    @Value("\${app.jwt.expired}")
    val expired: Long,
) {

    fun getSecretKey(): SecretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey))
}