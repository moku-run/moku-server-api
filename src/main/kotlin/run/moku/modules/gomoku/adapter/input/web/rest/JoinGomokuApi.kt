package run.moku.modules.gomoku.adapter.input.web.rest

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import run.moku.framework.security.auth.AuthenticationDTO


@RestController
class JoinGomokuApi {

    @PostMapping("/api/gomokus")
    fun getJoinGomoku(
        @AuthenticationPrincipal auth: AuthenticationDTO
    ) {
//        println("hihihhihihihihi")
        println(auth)
    }
}