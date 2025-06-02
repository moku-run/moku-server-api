package run.moku.modules.gomoku.domain.entity.player

import run.moku.modules.gomoku.domain.value.MokuStone

data class MokuPlayerHistory(
    val id: String,
    val stone: MokuStone,
)