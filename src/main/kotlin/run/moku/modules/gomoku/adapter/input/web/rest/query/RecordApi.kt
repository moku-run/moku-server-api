package run.moku.modules.gomoku.adapter.input.web.rest.query

import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import run.moku.framework.api.response.ApiResponse
import run.moku.framework.security.auth.AuthenticationDTO
import run.moku.modules.gomoku.adapter.input.web.rest.query.dto.RecordQueryDTO

@RestController
class RecordApi {

    @GetMapping("/api/records")
    fun getRecords(
        @AuthenticationPrincipal auth: AuthenticationDTO
    ): ResponseEntity<ApiResponse<RecordQueryDTO>> {
        return ApiResponse.success(
            payload = RecordQueryDTO(
                totalCount = 123,
                winCount = 23,
                loseCount = 100,
            )
        )
    }
}