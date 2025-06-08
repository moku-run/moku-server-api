package run.moku.modules.users.application.ports.input.query

import run.moku.modules.users.application.usecase.query.FetchUserModel

interface FetchUserInput {
    fun fetchByNickname(nickname: String): FetchUserModel
    fun fetchById(id: Long): FetchUserModel
}