package run.moku.modules.users.adapter.input.rest.query.dto

import run.moku.modules.users.domain.value.UserRole

interface UserQueryResponse {

    data class Details(
        val id: Long,
        val nickname: String,
        val loginId: String,
        val role: UserRole,
    )
}