package run.moku.modules.users.domain.model

import run.moku.modules.users.domain.entity.UserId
import run.moku.modules.users.domain.entity.UserLoginId
import run.moku.modules.users.domain.entity.UserNickname
import run.moku.modules.users.domain.value.UserPassword

class MokuUser(
    val userId: UserId,
    val loginId: UserLoginId,
    val nickname: UserNickname,
    val password: UserPassword,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MokuUser

        if (userId != other.userId) return false
        if (loginId != other.loginId) return false
        if (nickname != other.nickname) return false

        return true
    }

    override fun hashCode(): Int {
        var result = userId.hashCode()
        result = 31 * result + loginId.hashCode()
        result = 31 * result + nickname.hashCode()
        return result
    }
}