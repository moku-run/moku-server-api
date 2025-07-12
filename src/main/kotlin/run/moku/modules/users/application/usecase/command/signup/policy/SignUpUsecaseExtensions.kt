package run.moku.modules.users.application.usecase.command.signup.policy

import run.moku.modules.users.application.usecase.command.signup.model.UserSignUpModel
import run.moku.modules.users.domain.entity.UserId
import run.moku.modules.users.domain.entity.UserLoginId
import run.moku.modules.users.domain.entity.UserNickname
import run.moku.modules.users.domain.value.UserPassword
import run.moku.modules.users.domain.value.UserPasswordConfirm

inline fun SignUpUsecase.checkPassword(action: (UserPassword, UserPasswordConfirm) -> Unit) =
    action(model.password, model.passwordConfirm)

inline fun SignUpUsecase.checkDuplicateLoginId(action: (UserLoginId) -> Unit) =
    action(model.loginId)

inline fun SignUpUsecase.checkDuplicateNickname(action: (UserNickname) -> Unit) =
    action(model.nickname)

inline fun SignUpUsecase.signUp(action: (UserSignUpModel) -> UserId) {
    userId = action(model)
}

inline fun SignUpUsecase.publish(publish: (UserId) -> Unit) =
    publish(userId)