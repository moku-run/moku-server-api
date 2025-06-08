package run.moku.modules.users.application.usecase.query

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import run.moku.modules.users.application.ports.input.query.FetchUserInput
import run.moku.modules.users.application.ports.out.query.UserQueryPort

@Service
@Transactional(readOnly = true)
class FetchUserService(
    private val queryPort: UserQueryPort
) : FetchUserInput {

    override fun fetchById(id: Long): FetchUserModel =
        queryPort.fetchById(id)

    override fun fetchByNickname(nickname: String): FetchUserModel =
        queryPort.fetchByNickname(nickname)
}