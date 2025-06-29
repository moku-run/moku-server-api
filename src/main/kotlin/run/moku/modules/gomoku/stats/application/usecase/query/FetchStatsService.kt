package run.moku.modules.gomoku.stats.application.usecase.query

import org.springframework.stereotype.Service
import run.moku.modules.gomoku.stats.application.ports.input.query.FetchStatsInput
import run.moku.modules.gomoku.stats.application.ports.out.query.StatsQueryPort
import run.moku.modules.gomoku.player.domain.entity.MokuPlayer


@Service
class FetchStatsService(
    private val statsQueryPort: StatsQueryPort,
) : FetchStatsInput {

    override fun fetchDetails(mokuUser: MokuPlayer): FetchStatsModel =
        statsQueryPort.fetchDetails(mokuUser)

}