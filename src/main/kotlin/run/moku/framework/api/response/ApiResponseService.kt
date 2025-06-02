package run.moku.framework.api.response

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import run.moku.framework.api.response.ApiResponseService.Companion.defaultSetUp
import run.moku.framework.api.response.ApiResponseService.Companion.flush
import java.nio.charset.StandardCharsets

@Component
class ApiResponseService(
    private val objectMapper: ObjectMapper
) {

    fun <T> writeResponse(
        response: HttpServletResponse?,
        success: Boolean,
        apiResponseCode: ApiResponseCode,
        payload: T? = null
    ) {
        response.withResponse {
            status = apiResponseCode.status.value()
            writer.write(
                objectMapper.writeValueAsString(
                    ApiResponse.of(
                        success = success,
                        status = apiResponseCode.status,
                        message = apiResponseCode.message,
                        code = apiResponseCode.code,
                        payload = payload
                    ).body
                )
            )
        }
    }

    companion object {
        fun defaultSetUp(response: HttpServletResponse?) {

            response?.contentType = MediaType.APPLICATION_JSON_VALUE
            response?.characterEncoding = StandardCharsets.UTF_8.name()
        }

        fun flush(response: HttpServletResponse?) {
            response?.writer?.flush()
            response?.flushBuffer()
        }
    }
}

inline fun HttpServletResponse?.withResponse(block: HttpServletResponse.() -> Unit) {
    this?.let {
        defaultSetUp(it)
        it.block()
        flush(it)
    }
}