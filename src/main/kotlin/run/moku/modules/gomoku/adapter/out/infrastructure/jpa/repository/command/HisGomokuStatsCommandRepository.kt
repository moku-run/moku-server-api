package run.moku.modules.gomoku.adapter.out.infrastructure.jpa.repository.command

import org.springframework.data.repository.Repository
import run.moku.modules.gomoku.adapter.out.infrastructure.jpa.entity.HisGomokuStatsJpaEntity

interface HisGomokuStatsCommandRepository : Repository<HisGomokuStatsJpaEntity, Long> {
    fun save(entity: HisGomokuStatsJpaEntity): HisGomokuStatsJpaEntity
}