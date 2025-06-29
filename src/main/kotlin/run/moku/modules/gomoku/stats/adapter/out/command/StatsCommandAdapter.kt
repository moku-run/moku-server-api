package run.moku.modules.gomoku.stats.adapter.out.command

import org.springframework.stereotype.Service
import run.moku.modules.gomoku.stats.adapter.out.infrastructure.jpa.entity.HisGomokuStatsJpaEntity
import run.moku.modules.gomoku.stats.adapter.out.infrastructure.jpa.repository.HisGomokuStatsCommandRepository
import run.moku.modules.gomoku.stats.application.ports.out.command.StatsCommandPort
import run.moku.modules.gomoku.player.domain.entity.MokuPlayer

@Service
class StatsCommandAdapter(
    private val repository: HisGomokuStatsCommandRepository,
) : StatsCommandPort {

    override fun init(mokuUser: MokuPlayer) {
        HisGomokuStatsJpaEntity
            .init(mokuUser)
            .let { repository.save(it) }
    }
}