package run.moku.framework.api.exception

import org.springframework.http.HttpStatus
import run.moku.framework.api.response.ApiResponseCode

class ApiException(
    private val apiResponseCode: ApiResponseCode
) : RuntimeException(apiResponseCode.message) {

    fun code(): String = apiResponseCode.code
    fun message(): String = apiResponseCode.message
    fun status(): HttpStatus = apiResponseCode.status
    fun getApiResponseCode(): ApiResponseCode = apiResponseCode
}