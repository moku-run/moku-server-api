package run.moku.modules.users.adapter.out.command

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import run.moku.modules.users.adapter.out.infrastructure.jpa.command.UserCommandRepository
import run.moku.modules.users.adapter.out.infrastructure.jpa.mapper.UserCommandMapper
import run.moku.modules.users.application.ports.out.command.UserCommandPort
import run.moku.modules.users.domain.model.UserSignUpModel

@Service
class UserCommandAdapter(
    private val repository: UserCommandRepository,
    private val userMapper: UserCommandMapper,
    private val passwordEncoder: PasswordEncoder,
) : UserCommandPort {

    override fun registry(user: UserSignUpModel) = userMapper
        .convert(user)
        .apply { password = passwordEncoder.encode(password) }
        .let(repository::save)
}