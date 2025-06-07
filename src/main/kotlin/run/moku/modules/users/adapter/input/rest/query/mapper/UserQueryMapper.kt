package run.moku.modules.users.adapter.input.rest.query.mapper

import org.springframework.stereotype.Service
import run.moku.modules.users.adapter.input.rest.query.dto.UserQueryResponse
import run.moku.modules.users.application.usecase.query.FetchUserModel

@Service
class UserQueryMapper {

    fun convert(model: FetchUserModel): UserQueryResponse.Details {
        return UserQueryResponse.Details(
            id = model.userId.value,
            loginId = model.userLoginId.value,
            nickname = model.userNickname.value,
            role = model.role,
        )
    }
}