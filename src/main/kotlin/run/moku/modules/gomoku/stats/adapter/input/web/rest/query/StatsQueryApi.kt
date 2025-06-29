package run.moku.modules.gomoku.stats.adapter.input.web.rest.query

import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import run.moku.framework.api.response.ApiResponse
import run.moku.framework.security.auth.AuthenticationDTO
import run.moku.modules.gomoku.stats.application.ports.input.query.FetchStatsInput
import run.moku.modules.gomoku.stats.application.usecase.query.FetchStatsModel
import run.moku.modules.gomoku.player.domain.entity.MokuPlayer

@RestController
class StatsQueryApi(
    private val fetchStatsInput: FetchStatsInput,
) {

    @GetMapping("/api/stats")
    fun getDetails(
        @AuthenticationPrincipal auth: AuthenticationDTO
    ): ResponseEntity<ApiResponse<FetchStatsModel>> {
        val response = fetchStatsInput.fetchDetails(MokuPlayer(auth.id))

        return ApiResponse.success(
            payload = response
        )
    }
}