package run.moku.modules.gomoku.adapter.out.infrastructure.jpa.repository.query

import org.springframework.data.repository.Repository
import run.moku.modules.gomoku.adapter.out.infrastructure.jpa.entity.HisGomokuJpaEntity

interface HisGomokuQueryRepository : Repository<HisGomokuJpaEntity, Long> {

}