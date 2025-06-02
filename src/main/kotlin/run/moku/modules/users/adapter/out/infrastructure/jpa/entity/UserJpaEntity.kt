package run.moku.modules.users.adapter.out.infrastructure.jpa.entity

import jakarta.persistence.*
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import run.moku.framework.jpa.entity.BaseJpaEntity
import run.moku.framework.jpa.entity.validateSelf
import run.moku.modules.users.domain.entity.UserLoginId
import run.moku.modules.users.domain.entity.UserNickname
import run.moku.modules.users.domain.value.UserPassword
import run.moku.modules.users.domain.value.UserRole

@Entity
@Table(name = "mst_users")
class UserJpaEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @field:NotEmpty(message = UserLoginId.REQUIRED_MESSAGE)
    @field:Pattern(regexp = UserLoginId.PATTERN_STRING, message = UserLoginId.PATTERN_MESSAGE)
    @Column(name = "login_id", nullable = false, unique = true)
    var loginId: String,

    @field:NotEmpty(message = UserPassword.REQUIRED_MESSAGE)
    @Column(nullable = false)
    var password: String,

    @field:NotEmpty(message = UserNickname.REQUIRED_MESSAGE)
    @field:Size(
        min = UserNickname.MIN_LENGTH,
        max = UserNickname.MAX_LENGTH,
        message = UserNickname.LENGTH_VALID_MESSAGE
    )
    @Column(nullable = false, unique = true)
    var nickname: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var role: UserRole = UserRole.PLAYER

) : BaseJpaEntity() {

    init {
        validateSelf()
    }
}