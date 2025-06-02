package run.moku.framework.security

import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.stereotype.Component
import run.moku.framework.security.handler.BaseAccessDeniedHandler
import run.moku.framework.security.handler.BaseAuthenticationEntryPoint

@Component
class BaseSecurity(
    private val baseAuthenticationEntryPoint: BaseAuthenticationEntryPoint,
    private val baseAccessDeniedHandler: BaseAccessDeniedHandler,
) : AbstractHttpConfigurer<BaseSecurity, HttpSecurity>() {

    override fun init(http: HttpSecurity) {
        http
            .exceptionHandling {
                it.authenticationEntryPoint(baseAuthenticationEntryPoint)
                    .accessDeniedHandler(baseAccessDeniedHandler)
            }

            .csrf { it.disable() }
            .formLogin { it.disable() }
            .securityContext { it.disable() }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .sessionFixation { sessionFixation -> sessionFixation.none() }
            }
            .headers {
                it.frameOptions { option -> option.disable() }
            }
    }
}