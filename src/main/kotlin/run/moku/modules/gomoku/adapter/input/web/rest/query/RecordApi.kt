package run.moku.modules.gomoku.adapter.input.web.rest.query

import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import run.moku.framework.api.response.ApiResponse
import run.moku.framework.security.auth.AuthenticationDTO
import run.moku.modules.gomoku.adapter.input.web.rest.query.Color.*
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

enum class Color(
    val r: Int,
    val g: Int,
    val b: Int,
) {
    RED(1, 2, 3),
    RED1(1, 2, 3),
    RED2(1, 2, 3),
    RED3(1, 2, 3),
    ;
}

fun e(color: Color) {
    when (color) {
        RED -> "hello"
        RED1 -> TODO()
        RED2 -> TODO()
        RED3 -> TODO()
    }

}