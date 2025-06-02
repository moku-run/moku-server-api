package run.moku.framework.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import run.moku.framework.security.auth.UserDetailsServiceAdapter

@Configuration
class SecurityConfig {

    @Bean
    fun bCryptPasswordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun authenticationManager(
        userDetailsServiceAdapter: UserDetailsServiceAdapter,
        passwordEncoder: PasswordEncoder,
    ): AuthenticationManager {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(userDetailsServiceAdapter)
        authProvider.setPasswordEncoder(passwordEncoder)
        return ProviderManager(authProvider)
    }
}