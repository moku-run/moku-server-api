package run.moku.framework.security.filter

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import run.moku.framework.security.BaseSecurity
import run.moku.framework.security.jwt.JwtValues

@Configuration
class ApiSecurityFilter(
    private val baseSecurity: BaseSecurity,

    private val jwtLoginFilter: JwtLoginFilter,
    private val jwtLogoutFilter: JwtLogoutFilter,
    private val jwtLogoutSuccessHandler: JwtLogoutSuccessHandler,

    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain =
        http
            .securityMatcher(ALL_URL)
            .with(baseSecurity, BaseSecurity::active)

            .logout {
                it
                    .logoutUrl("/api/logout")
                    .addLogoutHandler(jwtLogoutFilter)
                    .deleteCookies(JwtValues.AUTHENTICATION_HEADER)
                    .invalidateHttpSession(true)
                    .logoutSuccessHandler(jwtLogoutSuccessHandler)
            }

            .authorizeHttpRequests {
                it
                    .requestMatchers(*PERMIT_ALL_API).permitAll()
                    .requestMatchers(*AUTHENTICATED_API).authenticated()
                    .anyRequest().denyAll()
            }

            .addFilterAt(jwtLoginFilter, UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)

            .build()

    companion object {
        private const val ALL_URL = "/**"

        private val PERMIT_ALL_API = arrayOf(
            "/",
            "/ws", "/ws/**",
            "/api/users/sign-up",
            "/api/logout", "/api/login",
            "/api/moku/*/details"
        )

        private val AUTHENTICATED_API = arrayOf(
            "/api/**",
            "/api"
        )
    }
}