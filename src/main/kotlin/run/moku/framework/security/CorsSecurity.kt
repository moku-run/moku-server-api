package run.moku.framework.security

import org.springframework.stereotype.Component
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Component
class CorsSecurity {

    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration().apply {
            ALLOWED_ORIGIN.forEach { addAllowedOrigin(it) }
            ALLOWED_HEADER.forEach { addAllowedHeader(it) }
            ALLOWED_METHODS.forEach { addAllowedMethod(it) }
            EXPOSED_HEADER.forEach { addExposedHeader(it) }

            allowCredentials = true
            maxAge = 3600L
        }

        return UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration("/**", configuration)
        }
    }

    companion object {
        private val ALLOWED_ORIGIN = listOf(
            "https://dev-lkdcode.moku.run",
            "https://dev-lkdcode.moku.run:13000",

            "https://localhost:14000",
        )

        //        private val ALLOWED_HEADER = listOf("Content-Type", "Authorization", "X-Requested-With")
        private val ALLOWED_HEADER = listOf("*")

        private val ALLOWED_METHODS = listOf(
            "HEAD",
            "GET",
            "POST",
            "PUT",
            "DELETE",
            "OPTIONS",
            "PATCH",
            "UPGRADE",
            "PROPFIND",
        )

        private val EXPOSED_HEADER = listOf(
            "Set-Cookie",
            "Authorization",
        )
    }
}