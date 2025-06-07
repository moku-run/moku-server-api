package run.moku.framework.security.handler

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import run.moku.framework.api.response.ApiResponseCode
import run.moku.framework.api.response.ApiResponseService

@Component
class ApiAuthenticationEntryPoint(
    private val apiResponseService: ApiResponseService
) : AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {
        println(authException)
        println("ApiAuthenticationEntryPoint")
        apiResponseService.writeResponse<Unit>(
            response = response,
            isSuccess = false,
            apiResponseCode = ApiResponseCode.API_ACCESS_DENIED
        )
    }
}