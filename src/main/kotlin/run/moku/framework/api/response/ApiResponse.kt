package run.moku.framework.api.response

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity


@JsonPropertyOrder("success", "code", "message", "payload")
class ApiResponse<T> private constructor(
    val success: Boolean,
    val code: String? = null,
    val message: String,
    val payload: T?
) {

    companion object {

        fun <T> of(
            success: Boolean,
            status: HttpStatus,
            message: String,
            code: String,
            payload: T? = null
        ): ResponseEntity<ApiResponse<T>> = ResponseEntity
            .status(status)
            .body(ApiResponse(success, code, message, payload))

        fun <T> success(
            status: HttpStatus = HttpStatus.OK,
            message: String = ApiResponseCode.OK.message,
            code: String? = null,
            payload: T? = null
        ): ResponseEntity<ApiResponse<T>> = ResponseEntity
            .status(status)
            .body(ApiResponse(true, code, message, payload))

        fun <T> successCreated(
            message: String,
            code: String = ApiResponseCode.CREATED.code,
            payload: T? = null
        ): ResponseEntity<ApiResponse<T>> = ResponseEntity
            .status(HttpStatus.CREATED)
            .body(ApiResponse(true, code, message, payload))

        fun <T> successDeleted(
            message: String,
            code: String,
            payload: T? = null
        ): ResponseEntity<ApiResponse<T>> = ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(ApiResponse(true, code, message, payload))

        fun <T> clientError(
            status: HttpStatus = HttpStatus.BAD_REQUEST,
            message: String,
            code: String,
            payload: T? = null
        ): ResponseEntity<ApiResponse<T>> = ResponseEntity
            .status(status)
            .body(ApiResponse(false, code, message, payload))

        fun <T> clientError(
            apiResponseCode: ApiResponseCode,
            payload: T? = null
        ): ResponseEntity<ApiResponse<T>> = ResponseEntity
            .status(apiResponseCode.status)
            .body(ApiResponse(false, apiResponseCode.code, apiResponseCode.message, payload))

        fun <T> serverError(
            apiResponseCode: ApiResponseCode,
            payload: T? = null
        ): ResponseEntity<ApiResponse<T>> = ResponseEntity
            .status(apiResponseCode.status)
            .body(ApiResponse(false, apiResponseCode.code, apiResponseCode.message, payload))
    }
}