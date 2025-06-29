package run.moku.modules.gomoku.stats.adapter.out.query

import org.springframework.stereotype.Service
import run.moku.modules.gomoku.stats.adapter.out.infrastructure.jpa.repository.HisGomokuStatsQueryRepository
import run.moku.modules.gomoku.stats.adapter.out.infrastructure.jpa.repository.loadById
import run.moku.modules.gomoku.stats.application.ports.out.query.StatsQueryPort
import run.moku.modules.gomoku.stats.application.usecase.query.FetchStatsModel
import run.moku.modules.gomoku.player.domain.entity.MokuPlayer

@Service
class StatsQueryAdapter(
    private val repository: HisGomokuStatsQueryRepository
) : StatsQueryPort {

    override fun fetchDetails(mokuUser: MokuPlayer): FetchStatsModel =
        repository
            .loadById(mokuUser.value)
            .convertModel()
}