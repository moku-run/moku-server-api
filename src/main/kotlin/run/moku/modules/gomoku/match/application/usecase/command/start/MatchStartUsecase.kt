package run.moku.modules.gomoku.match.application.usecase.command.start

class MatchStartUsecase private constructor() {

    companion object {
        fun execute(block: MatchStartUsecase.() -> Unit) =
            block(MatchStartUsecase())
    }
}