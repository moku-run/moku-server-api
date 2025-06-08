package run.moku.framework.api.response

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@JsonPropertyOrder("success", "code", "message", "payload")
class ApiResponse<T>(
    val success: Boolean = true,
    val code: String? = null,
    val message: String,
    val payload: T? = null,
) {
    companion object {

        fun <T> ofDTO(
            success: Boolean,
            apiCode: ApiResponseCode,
            payload: T? = null
        ): ApiResponse<T> = ApiResponse(success, apiCode.code, apiCode.message, payload)

        fun <T> success(
            apiCode: ApiResponseCode = ApiResponseCode.OK,
            payload: T? = null
        ): ResponseEntity<ApiResponse<T>> = ResponseEntity
            .status(apiCode.status)
            .body(
                ApiResponse(
                    code = apiCode.code,
                    message = apiCode.message,
                    payload = payload
                )
            )

        fun <T> successCreated(payload: T? = null): ResponseEntity<ApiResponse<T>> = ResponseEntity
            .status(ApiResponseCode.CREATED.status)
            .body(
                ApiResponse(
                    code = ApiResponseCode.CREATED.code,
                    message = ApiResponseCode.CREATED.message,
                    payload = payload,
                )
            )

        fun <T> successUpdated(payload: T? = null): ResponseEntity<ApiResponse<T>> = ResponseEntity
            .status(ApiResponseCode.UPDATED.status)
            .body(
                ApiResponse(
                    code = ApiResponseCode.UPDATED.code,
                    message = ApiResponseCode.UPDATED.message,
                    payload = payload,
                )
            )

        fun <T> successDeleted(payload: T? = null): ResponseEntity<ApiResponse<T>> = ResponseEntity
            .status(ApiResponseCode.DELETED.status)
            .body(
                ApiResponse(
                    code = ApiResponseCode.DELETED.code,
                    message = ApiResponseCode.DELETED.message,
                    payload = payload,
                )
            )

        fun <T> clientError(
            apiResponseCode: ApiResponseCode = ApiResponseCode.REQUEST_INVALID,
            payload: T? = null
        ): ResponseEntity<ApiResponse<T>> = ResponseEntity
            .status(apiResponseCode.status)
            .body(ApiResponse(false, apiResponseCode.code, apiResponseCode.message, payload))

        fun <T> serverError(
            apiResponseCode: ApiResponseCode = ApiResponseCode.SEVER_UNHANDLED_EXCEPTION,
            payload: T? = null
        ): ResponseEntity<ApiResponse<T>> = ResponseEntity
            .status(apiResponseCode.status)
            .body(ApiResponse(false, apiResponseCode.code, apiResponseCode.message, payload))
    }
}