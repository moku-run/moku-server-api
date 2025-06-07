package run.moku.framework.security.handler

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import run.moku.framework.api.response.ApiResponseCode
import run.moku.framework.api.response.ApiResponseService
import run.moku.framework.log.log

@Component
class ApiAuthenticationEntryPoint(
    private val apiResponseService: ApiResponseService
) : AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {
        log().info("$authException")

        apiResponseService.writeResponse<Unit>(
            response = response,
            isSuccess = false,
            apiResponseCode = ApiResponseCode.AUTHENTICATION_REQUIRED
        )
    }
}