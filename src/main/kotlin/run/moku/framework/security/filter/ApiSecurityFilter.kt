package run.moku.framework.security.filter

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import run.moku.framework.security.BaseSecurity
import run.moku.framework.security.CorsSecurity

@Configuration
class ApiSecurityFilter(
    private val baseSecurity: BaseSecurity,

    private val jwtLoginFilter: JwtLoginFilter,
    private val jwtLogoutFilter: JwtLogoutFilter,
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain =
        http
            .securityMatcher(ALL_URL)
            .also { baseSecurity.init(it) }
            .logout {
                it
                    .logoutUrl("/api/logout")
                    .addLogoutHandler(jwtLogoutFilter)
            }

            .authorizeHttpRequests {
                it
                    .requestMatchers(*PERMIT_ALL_API).permitAll()
                    .requestMatchers(*AUTHENTICATED_API).permitAll()
                    .anyRequest().denyAll()
            }

            .addFilterAt(jwtLoginFilter, UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)

            .build()

    companion object {
        private const val ALL_URL = "/**"

        private val PERMIT_ALL_API = arrayOf(
            "/",
            "/api/logout", "/api/login",
        )

        private val AUTHENTICATED_API = arrayOf(
            "/api/**",
            "/api"
        )
    }
}