package run.moku.modules.users.application.usecase.command.signup.policy

import run.moku.modules.users.application.usecase.command.signup.model.UserSignUpModel
import run.moku.modules.users.domain.entity.UserLoginId
import run.moku.modules.users.domain.entity.UserNickname

inline fun SignUpUsecase.checkDuplicateLoginId(action: (UserLoginId) -> Unit) =
    action(model.loginId)

inline fun SignUpUsecase.checkDuplicateNickname(action: (UserNickname) -> Unit) =
    action(model.nickname)

inline fun SignUpUsecase.signUp(action: (UserSignUpModel) -> Unit) =
    action(model)