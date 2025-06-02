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
            "http://dev-lkdcode.moku.run:5173",
            "http://dev-lkdcode.moku.run",

            "https://dev-lkdcode.moku.run",
            "https://dev-lkdcode.moku.run:5173",
//            "http://192.168.0.6:5173",
//            "http://183.101.130.89:5173",
//            "http://lkdcode.iptime.org:5173",
//            "https://183.101.130.89:5173",
//            "https://lkdcode.iptime.org:5173",
//            "http://localhost:5173",
//            "http://lkdcode.moku.run:5173",
//            "http://lkdcode.moku.run:18080",
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