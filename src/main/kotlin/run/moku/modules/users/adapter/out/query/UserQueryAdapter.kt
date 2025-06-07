package run.moku.modules.users.adapter.out.query

import org.springframework.stereotype.Service
import run.moku.modules.users.adapter.out.infrastructure.jpa.query.*
import run.moku.modules.users.application.ports.out.query.UserQueryPort
import run.moku.modules.users.application.usecase.query.FetchUserModel
import run.moku.modules.users.domain.entity.UserLoginId
import run.moku.modules.users.domain.entity.UserNickname

@Service
class QueryUserAdapter(
    private val repository: UserQueryRepository
) : UserQueryPort {

    override fun fetchById(id: Long): FetchUserModel =
        repository
            .loadById(id)
            .convert()

    override fun fetchByNickname(nickname: String): FetchUserModel =
        repository
            .loadByNickname(nickname)
            .convert()

    override fun existsLoginId(loginId: UserLoginId): Boolean =
        repository.existsByLoginId(loginId.value)

    override fun existsNickname(nickname: UserNickname): Boolean =
        repository.existsByNickname(nickname.value)

}