package run.moku.modules.gomoku.player.adapter.out.validator

import org.springframework.stereotype.Service
import run.moku.modules.gomoku.player.domain.entity.MokuPlayer
import run.moku.modules.gomoku.stats.application.ports.out.validator.MokuUserValidator
import run.moku.modules.users.application.ports.out.validator.UserValidator
import run.moku.modules.users.domain.entity.UserId

@Service
class MokuUserValidatorAdapter(
    private val userValidator: UserValidator
) : MokuUserValidator {

    override fun validate(mokuUser: MokuPlayer) {
        userValidator.validate(UserId.of(mokuUser.value))
    }
}