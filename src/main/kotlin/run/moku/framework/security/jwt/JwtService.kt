package run.moku.framework.security.jwt

import org.springframework.stereotype.Service
import run.moku.framework.security.auth.AuthenticationDTO

@Service
class JwtService(
    private val properties: JwtProperties,
    private val creator: JwtCreator,
    private val parser: JwtParser,
) {

    fun createToken(username: String): String = creator.create(username)

    fun validToken(token: String?): Boolean {
        println("token: ${token}")

        return true
    }

    fun getUsername(token: String): String = parser
        .getClaims(token)[JwtValues.USERNAME_KEY] as String

    fun authorizationHeader(): String = JwtValues.AUTHENTICATION_HEADER
}