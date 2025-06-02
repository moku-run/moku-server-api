package run.moku.modules.users.domain.entity

@JvmInline
value class UserLoginId private constructor(
    val value: String
) {
    init {
        require(value.isNotEmpty()) { REQUIRED_MESSAGE }
        require(PATTERN.matches(value)) { PATTERN_MESSAGE }
        require(value.length in MIN_LENGTH..MAX_LENGTH) { LENGTH_MESSAGE }
    }

    companion object {
        const val REQUIRED_MESSAGE = "로그인 아이디는 필수입니다."

        const val PATTERN_STRING = "^[a-zA-Z0-9._\\-!@#$%^&*()]{4,20}$"
        const val PATTERN_MESSAGE =
            "로그인 아이디는 영어 대,소문자 숫자, 특수문자는 '.','_','-','!','@','#','$','%','^','&','*','(',')' 만 사용가능합니다."

        const val MIN_LENGTH = 4;
        const val MAX_LENGTH = 20;
        const val LENGTH_MESSAGE = "로그인 아이디는 ${MIN_LENGTH}~${MAX_LENGTH}자리만 가능합니다."

        val PATTERN = Regex(PATTERN_STRING)

        fun of(value: String): UserLoginId = UserLoginId(value)
    }
}