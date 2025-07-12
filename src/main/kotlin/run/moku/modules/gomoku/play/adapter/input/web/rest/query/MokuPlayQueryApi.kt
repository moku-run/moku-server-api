package run.moku.modules.gomoku.play.adapter.input.web.rest.query

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import run.moku.framework.api.response.ApiResponse
import run.moku.modules.gomoku.board.domain.entity.BoardId

@RestController
class MokuPlayQueryApi(

) {

    @GetMapping("/api/moku/{roomId}/details")
    fun getRoomDetails(
        @PathVariable(name = "roomId") roomId: String
    ): ResponseEntity<ApiResponse<String>> {
        println(roomId)





        BoardId(roomId)
        // TODO roomId 기준 전적과
        return ApiResponse.success(payload = "hihi")
    }
}

data class MatchingInformation(
    val whitePlayerNickname: String,
    val whitePlayerWinCount: Int,
    val whitePlayerLoseCount: Int,
    val whitePlayerTotalCount: Int,

    val blackPlayerNickname: String,
    val blackPlayerWinCount: Int,
    val blackPlayerLoseCount: Int,
    val blackPlayerTotalCount: Int,
)