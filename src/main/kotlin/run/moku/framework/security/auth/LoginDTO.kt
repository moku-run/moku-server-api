package run.moku.framework.security.auth

data class LoginDTO(
    val loginId: String,
    val password: String
)