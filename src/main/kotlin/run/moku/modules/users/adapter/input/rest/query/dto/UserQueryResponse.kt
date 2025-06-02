package run.moku.modules.users.adapter.input.rest.query.dto

interface UserQueryResponse {

    data class Get(
        val nickname: String,
        val loginId: String,
    )
}