package run.moku.modules.gomoku.application.usecase.stats.query

import org.springframework.stereotype.Service
import run.moku.modules.gomoku.application.ports.input.FetchStatsInput
import run.moku.modules.gomoku.application.ports.out.query.StatsQueryPort
import run.moku.modules.gomoku.domain.entity.user.MokuUser


@Service
class FetchStatsService(
    private val statsQueryPort: StatsQueryPort,
) : FetchStatsInput {

    override fun fetchDetails(mokuUser: MokuUser): FetchStatsModel =
        statsQueryPort.fetchDetails(mokuUser)

}