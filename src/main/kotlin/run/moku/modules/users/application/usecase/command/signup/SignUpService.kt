package run.moku.modules.users.application.usecase.command.signup

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import run.moku.modules.users.application.ports.input.command.SignUpInput
import run.moku.modules.users.application.ports.out.command.UserCommandPort
import run.moku.modules.users.application.ports.out.event.UserEventPublisher
import run.moku.modules.users.application.ports.out.validator.UserValidator
import run.moku.modules.users.application.usecase.command.signup.model.UserSignUpModel
import run.moku.modules.users.application.usecase.command.signup.policy.*

@Service
@Transactional
class SignUpService(
    private val validator: UserValidator,
    private val commandPort: UserCommandPort,
    private val eventPublisher: UserEventPublisher,
) : SignUpInput {

    override fun perform(model: UserSignUpModel) {
        SignUpUsecase.execute(model) {
            checkDuplicateLoginId(validator::checkDuplicateLoginId)
            checkDuplicateNickname(validator::checkDuplicateNickname)
            signUp(commandPort::registry)
            publish(eventPublisher::signUpEvent)
        }
    }
}