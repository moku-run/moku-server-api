package run.moku.modules.gomoku.stats.adapter.out.infrastructure.jpa.repository

import org.springframework.data.repository.Repository
import run.moku.modules.gomoku.stats.adapter.out.infrastructure.jpa.entity.HisGomokuStatsJpaEntity

interface HisGomokuStatsCommandRepository : Repository<HisGomokuStatsJpaEntity, Long> {
    fun save(entity: HisGomokuStatsJpaEntity): HisGomokuStatsJpaEntity
}