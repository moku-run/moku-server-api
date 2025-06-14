package run.moku.modules.gomoku.adapter.out.infrastructure.jpa.repository.query

import org.springframework.data.repository.Repository
import run.moku.framework.api.exception.ApiException
import run.moku.framework.api.response.ApiResponseCode
import run.moku.modules.gomoku.adapter.out.infrastructure.jpa.entity.HisGomokuStatsJpaEntity

interface HisGomokuStatsQueryRepository : Repository<HisGomokuStatsJpaEntity, Long> {
    fun findById(userId: Long): HisGomokuStatsJpaEntity?
}

fun HisGomokuStatsQueryRepository.loadById(userId: Long): HisGomokuStatsJpaEntity =
    findById(userId) ?: throw ApiException(ApiResponseCode.OK)