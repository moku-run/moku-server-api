package run.moku.modules.gomoku.adapter.out.infrastructure.jpa.repository

import org.springframework.data.repository.Repository
import run.moku.modules.gomoku.adapter.out.infrastructure.jpa.entity.HisGomokuJpaEntity

interface HisGomokuRepository : Repository<HisGomokuJpaEntity, Long> {
}