package run.moku.modules.gomoku.application.usecase.stats.command

import run.moku.modules.gomoku.domain.entity.user.MokuUser

class CreateStatsUsecase private constructor(
    val user: MokuUser
) {

    companion object {
        fun execute(user: MokuUser, block: CreateStatsUsecase.() -> Unit) =
            block(CreateStatsUsecase(user))
    }
}