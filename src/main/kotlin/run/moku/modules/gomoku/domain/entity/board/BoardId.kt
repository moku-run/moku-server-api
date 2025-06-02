package run.moku.modules.gomoku.domain.entity.board

import java.util.UUID

@JvmInline
value class BoardId(
    val value: String
) {

    companion object {
        fun init(): BoardId =
            BoardId(UUID.randomUUID().toString())
    }
}