package run.moku.modules.gomoku.play.domain.entity

import run.moku.modules.gomoku.play.domain.value.MokuStone

data class MokuPlayerHistory(
    val id: String,
    val stone: MokuStone,
)