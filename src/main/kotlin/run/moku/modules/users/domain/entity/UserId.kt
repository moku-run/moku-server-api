package run.moku.modules.users.domain.entity

@JvmInline
value class UserId private constructor(
    val value: Long
) {
    init {
        require(value > MIN_VALUE) { VALUE_ERROR_MESSAGE }
    }

    companion object {
        const val MIN_VALUE = 0
        const val VALUE_ERROR_MESSAGE = "유저의 식별자는 음수가 될 수 없습니다."

        fun of(id: Long) = UserId(id)
    }
}