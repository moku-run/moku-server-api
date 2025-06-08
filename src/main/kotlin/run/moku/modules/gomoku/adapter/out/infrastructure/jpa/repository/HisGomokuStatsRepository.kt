package run.moku.modules.gomoku.adapter.out.infrastructure.jpa.repository

import org.springframework.data.repository.Repository
import run.moku.modules.gomoku.adapter.out.infrastructure.jpa.entity.HisGomokuStatsJpaEntity

interface HisGomokuStatsRepository : Repository<HisGomokuStatsJpaEntity, Long> {
}