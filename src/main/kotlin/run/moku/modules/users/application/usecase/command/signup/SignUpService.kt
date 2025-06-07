package run.moku.modules.users.application.usecase.command.signup

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import run.moku.modules.users.application.ports.input.command.SignUpInput
import run.moku.modules.users.application.ports.out.command.UserCommandPort
import run.moku.modules.users.application.ports.out.validator.UserValidator
import run.moku.modules.users.application.usecase.command.signup.policy.SignUpUsecase
import run.moku.modules.users.application.usecase.command.signup.policy.checkDuplicateLoginId
import run.moku.modules.users.application.usecase.command.signup.policy.checkDuplicateNickname
import run.moku.modules.users.application.usecase.command.signup.policy.signUp
import run.moku.modules.users.application.usecase.command.signup.model.UserSignUpModel

@Service
@Transactional
class SignUpService(
    private val validator: UserValidator,
    private val commandPort: UserCommandPort,
) : SignUpInput {

    override fun perform(model: UserSignUpModel) {
        SignUpUsecase.execute(model) {
            checkDuplicateLoginId(validator::checkDuplicateLoginId)
            checkDuplicateNickname(validator::checkDuplicateNickname)
            signUp(commandPort::registry)
        }
    }
}