package run.moku.framework.security.filter

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import run.moku.framework.security.BaseSecurity
import run.moku.framework.security.CorsSecurity

@Configuration
class BaseSecurityFilter(
    private val baseSecurity: BaseSecurity,
    private val jwtLoginFilter: JwtLoginFilter,
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val corsSecurity: CorsSecurity,
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain =
        http
            .securityMatcher(ALL_URL)
            .also { baseSecurity.init(it) }
            .cors { it.configurationSource(corsSecurity.corsConfigurationSource()) }
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it
                    .requestMatchers(HttpMethod.OPTIONS).permitAll()
                    .requestMatchers(*PERMIT_ALL_API).permitAll()
                    .anyRequest().authenticated()
            }
//            .logout(logoutConfigurer -> logoutConfigurer.logoutUrl("/logout")
//        .addLogoutHandler(jwtLogoutFilter))

            .addFilterAt(jwtLoginFilter, UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)

            .build()

    companion object {
        private const val ALL_URL = "/**"

        private val PERMIT_ALL_API = arrayOf(
            "/**",
        )
    }
}