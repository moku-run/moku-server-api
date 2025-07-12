package run.moku.modules.gomoku.play.domain.value

import java.util.*

@JvmInline
value class MokuPlayHistory private constructor(
    private val history: Stack<MokuPlayStone>
) {

    fun record(mokuPlayStone: MokuPlayStone) {
        this.history.push(mokuPlayStone)
    }

    companion object {
        fun init(): MokuPlayHistory =
            MokuPlayHistory(Stack<MokuPlayStone>())
    }
}