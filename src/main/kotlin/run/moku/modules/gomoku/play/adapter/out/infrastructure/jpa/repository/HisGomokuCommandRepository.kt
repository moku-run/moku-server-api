package run.moku.modules.gomoku.play.adapter.out.infrastructure.jpa.repository

import org.springframework.data.repository.Repository
import run.moku.modules.gomoku.play.adapter.out.infrastructure.jpa.entity.HisGomokuJpaEntity

interface HisGomokuCommandRepository : Repository<HisGomokuJpaEntity, Long> {
    fun save(entity: HisGomokuJpaEntity)
}