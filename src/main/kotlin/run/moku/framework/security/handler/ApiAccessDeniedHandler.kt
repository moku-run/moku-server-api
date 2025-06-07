package run.moku.framework.security.handler

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import run.moku.framework.api.response.ApiResponseCode
import run.moku.framework.api.response.ApiResponseService

@Component
class ApiAccessDeniedHandler(
    private val apiResponseService: ApiResponseService
) : AccessDeniedHandler {
    override fun handle(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        accessDeniedException: AccessDeniedException?
    ) {
        println(accessDeniedException)
        println("ApiAccessDeniedHandler")
        apiResponseService.writeResponse<Unit>(
            response = response,
            isSuccess = false,
            apiResponseCode = ApiResponseCode.AUTHENTICATION_REQUIRED
        )
    }
}