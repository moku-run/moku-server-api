package run.moku.modules.gomoku.adapter.out.query

import org.springframework.stereotype.Service
import run.moku.modules.gomoku.adapter.out.infrastructure.jpa.repository.query.HisGomokuStatsQueryRepository
import run.moku.modules.gomoku.adapter.out.infrastructure.jpa.repository.query.loadById
import run.moku.modules.gomoku.application.ports.out.query.StatsQueryPort
import run.moku.modules.gomoku.application.usecase.stats.query.FetchStatsModel
import run.moku.modules.gomoku.domain.entity.user.MokuUser

@Service
class StatsQueryAdapter(
    private val repository: HisGomokuStatsQueryRepository
) : StatsQueryPort {

    override fun fetchDetails(mokuUser: MokuUser): FetchStatsModel =
        repository
            .loadById(mokuUser.value)
            .convertModel()
}