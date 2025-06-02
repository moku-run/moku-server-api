package run.moku.modules.gomoku.domain.value

enum class MokuStone {
    BLACK_STONE(),
    WHITE_STONE(),

    ;

    fun toggle(): MokuStone = if (this == BLACK_STONE) WHITE_STONE else BLACK_STONE
}