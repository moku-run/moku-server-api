package run.moku.framework.api.exception

import org.springframework.http.HttpStatus
import run.moku.framework.api.response.ApiResponseCode

class ApiException(
    private val response: ApiResponseCode,
    val payload: Any? = null
) : RuntimeException(response.message) {

    fun name(): String = response.name
    fun code(): String = response.code
    fun status(): HttpStatus = response.status
    fun message(): String = response.message
    fun getApiResponseCode(): ApiResponseCode = response
}