package run.moku.modules.users.adapter.out.infrastructure.jpa.mapper

import org.springframework.stereotype.Service
import run.moku.modules.users.adapter.out.infrastructure.jpa.entity.UserJpaEntity
import run.moku.modules.users.domain.model.UserSignUpModel

@Service
class UserCommandMapper {

    fun convert(target: UserSignUpModel) = UserJpaEntity(
        loginId = target.loginId.value,
        password = target.password.value,
        nickname = target.nickname.value,
    )
}