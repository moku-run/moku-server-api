package run.moku.framework.security.jwt

import org.springframework.stereotype.Service

@Service
class JwtService(
    private val creator: JwtCreator,
    private val parser: JwtParser,
    private val validator: JwtValidator,
    private val remover: JwtRemover,
) {

    fun createToken(username: String): String =
        creator.create(
            mutableMapOf(
                JwtValues.USERNAME_KEY to username,
            )
        )

    fun validToken(token: String): Boolean =
        validator.validate(token)

    fun getUsername(token: String): String? =
        parser.getUsername(token)

    fun remove(token: String) =
        remover.remove(token)
}