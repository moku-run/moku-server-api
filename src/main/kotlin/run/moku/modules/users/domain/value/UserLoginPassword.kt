package run.moku.modules.users.domain.value

@JvmInline
value class UserLoginPassword private constructor(
    val value: String
) {
    init {
        require(value.isNotEmpty()) { REQUIRED_MESSAGE }
    }

    companion object {
        const val REQUIRED_MESSAGE = "로그인 비밀번호는 필수입니다."

        fun of(value: String): UserLoginPassword = UserLoginPassword(value)
    }
}