package run.moku.framework.api.advice

import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.ConstraintViolationException
import org.springframework.dao.DataAccessException
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingRequestHeaderException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.resource.NoResourceFoundException
import run.moku.framework.api.exception.ApiException
import run.moku.framework.api.response.ApiResponse
import run.moku.framework.api.response.ApiResponseCode
import run.moku.framework.log.log
import java.sql.SQLException

@RestControllerAdvice
class ApiExceptionAdvice {

    @ExceptionHandler(ApiException::class)
    fun handleApiException(e: ApiException): ResponseEntity<ApiResponse<Any>> {
        log().info("ApiResponseCode: ${e.getApiResponseCode()}, Message: ${e.message()} Status: ${e.status()}")

        return ApiResponse.clientError(e.getApiResponseCode(), e.payload)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<ApiResponse<List<*>>> {
        val response = e.bindingResult.fieldErrors.map {
            log().info("Field: ${it.field}, Rejected value: ${it.rejectedValue}, Message: ${it.defaultMessage}")

            mapOf(
                "field" to it.field,
                "rejected value" to it.rejectedValue,
                "message" to it.defaultMessage
            )
        }

        return ApiResponse.clientError(
            apiResponseCode = ApiResponseCode.REQUEST_INVALID,
            payload = response
        )
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(e: ConstraintViolationException): ResponseEntity<ApiResponse<List<*>>> {
        val response = e.constraintViolations.map {
            log().info("Path: ${it.rootBeanClass}, Property: ${it.propertyPath} Invalid value: ${it.invalidValue}, Message: ${it.message}")

            mapOf(
                "Invalid value" to it.propertyPath.toString(),
                "Message" to it.message
            )
        }

        return ApiResponse.clientError(
            apiResponseCode = ApiResponseCode.REQUEST_INVALID_DATA,
            payload = response,
        )
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(e: HttpMessageNotReadableException): ResponseEntity<ApiResponse<String>> {
        log().info("HttpMessageNotReadableException ${e.message}")

        return ApiResponse.clientError(
            apiResponseCode = ApiResponseCode.REQUEST_INVALID_BODY,
            payload = "${e.message}",
        )
    }

    @ExceptionHandler(MissingRequestHeaderException::class)
    fun handleMissingRequestHeaderException(e: MissingRequestHeaderException): ResponseEntity<ApiResponse<String>> {
        log().info("${e.body.detail}")

        return ApiResponse.clientError(
            apiResponseCode = ApiResponseCode.REQUEST_MISSING_HEADER,
            payload = "${e.body.detail}",
        )
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun handleHttpRequestMethodNotSupportedException(
        e: HttpRequestMethodNotSupportedException,
        request: HttpServletRequest,
    ): ResponseEntity<ApiResponse<String>> {
        log().info("Method: ${e.method} End-Point: ${request.requestURI}")

        return ApiResponse.clientError(
            apiResponseCode = ApiResponseCode.REQUEST_UNSUPPORTED_METHOD,
            payload = "${e.method} ${request.requestURI} ${e.message}",
        )
    }

    @ExceptionHandler(NoResourceFoundException::class)
    fun handleNoResourceFoundException(
        e: NoResourceFoundException
    ): ResponseEntity<ApiResponse<String>> {
        log().info("End-Point: ${e.resourcePath} Message: ${e.message}")

        return ApiResponse.clientError(
            apiResponseCode = ApiResponseCode.REQUEST_UNSUPPORTED_REQUEST,
            payload = "API: /${e.resourcePath}, Message: ${e.message}",
        )
    }

    @ExceptionHandler(SQLException::class)
    fun handleSQLException(e: SQLException): ResponseEntity<ApiResponse<Unit>> {
        log().warn("SQLException!!")

        return ApiResponse.serverError(
            apiResponseCode = ApiResponseCode.SEVER_SQL_EXCEPTION
        )
    }

    @ExceptionHandler(DataAccessException::class)
    fun handleDataAccessException(e: DataAccessException): ResponseEntity<ApiResponse<Unit>> {
        log().warn("DataAccessException Message: ${e.message}")

        return ApiResponse.serverError(
            apiResponseCode = ApiResponseCode.SEVER_DATABASE_EXCEPTION
        )
    }

    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeException(e: RuntimeException): ResponseEntity<ApiResponse<Unit>> {
        log().error("[Runtime Exception] ${e::class.simpleName}: ${e.message}", e)

        return ApiResponse.serverError(
            apiResponseCode = ApiResponseCode.SEVER_UNHANDLED_EXCEPTION
        )
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ApiResponse<Unit>> {
        log().error("[Unhandled Exception] ${e::class.simpleName}: ${e.message}", e)

        return ApiResponse.serverError(
            apiResponseCode = ApiResponseCode.SEVER_CRITICAL_EXCEPTION
        )
    }
}