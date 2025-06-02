package run.moku.modules.users.domain.value

class UserPassword private constructor(
    val value: String
) {
    init {
        require(value.isNotEmpty()) { REQUIRED_MESSAGE }
        require(PATTERN.matches(value)) { VALID_PATTERN_MESSAGE }
    }

    companion object {
        private const val MAX_LENGTH = 20
        private const val MIN_LENGTH = 8

        const val REQUIRED_MESSAGE = "비밀번호는 필수입니다."

        const val PATTERN_STRING = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#\$%^&*])[A-Za-z\\d!@#\$%^&*]{8,20}$";
        const val VALID_PATTERN_MESSAGE =
            "비밀번호는 영어, 숫자, 특수문자 ['!','@','#','$','%','^','&','*'] 조합과 ${MIN_LENGTH}~${MAX_LENGTH}자리만 허용됩니다."

        private val PATTERN = Regex(PATTERN_STRING)

        fun of(value: String): UserPassword = UserPassword(value)
    }
}