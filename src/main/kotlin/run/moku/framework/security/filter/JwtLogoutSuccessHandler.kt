package run.moku.framework.security.filter

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler
import org.springframework.stereotype.Component
import run.moku.framework.api.response.ApiResponseCode
import run.moku.framework.api.response.ApiResponseService

@Component
class JwtLogoutSuccessHandler(
    private val apiResponseService: ApiResponseService
) : LogoutSuccessHandler {

    override fun onLogoutSuccess(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authentication: Authentication?
    ) {
        apiResponseService.writeResponse<Unit>(
            response = response,
            isSuccess = true,
            apiResponseCode = ApiResponseCode.OK,
        )
    }
}