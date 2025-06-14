package run.moku.modules.gomoku.adapter.out.command

import org.springframework.stereotype.Service
import run.moku.modules.gomoku.adapter.out.infrastructure.jpa.entity.HisGomokuStatsJpaEntity
import run.moku.modules.gomoku.adapter.out.infrastructure.jpa.repository.command.HisGomokuStatsCommandRepository
import run.moku.modules.gomoku.application.ports.out.command.StatsCommandPort
import run.moku.modules.gomoku.domain.entity.user.MokuUser

@Service
class StatsCommandAdapter(
    private val repository: HisGomokuStatsCommandRepository,
) : StatsCommandPort {

    override fun init(mokuUser: MokuUser) {
        HisGomokuStatsJpaEntity
            .init(mokuUser)
            .let { repository.save(it) }
    }
}