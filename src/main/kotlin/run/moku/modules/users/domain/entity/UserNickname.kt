package run.moku.modules.users.domain.entity

@JvmInline
value class UserNickname private constructor(
    val value: String
) {
    init {
        require(value.isNotEmpty()) { REQUIRED_MESSAGE }
        require(value.length in MIN_LENGTH..MAX_LENGTH) { LENGTH_VALID_MESSAGE }
    }

    companion object {
        const val REQUIRED_MESSAGE = "닉네임은 필수입니다."

        const val MIN_LENGTH = 4;
        const val MAX_LENGTH = 20;
        const val LENGTH_VALID_MESSAGE = "닉네임은 ${UserLoginId.MIN_LENGTH}~${UserLoginId.MAX_LENGTH}자리만 허용됩니다.";

        fun of(value: String): UserNickname = UserNickname(value)
    }
}