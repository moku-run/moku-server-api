package run.moku.framework.security.auth

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import run.moku.modules.users.domain.value.UserRole

data class AuthenticationDTO(
    val role: UserRole = UserRole.PLAYER,
    val id: Long?,
    val loginId: String,
    val nickname: String,
    val encodedPassword: String,
    val isNotDeleted: Boolean = true,
    val isNonLocked: Boolean = true,
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        mutableListOf(SimpleGrantedAuthority(role.name))

    override fun getPassword(): String = encodedPassword

    override fun getUsername(): String = loginId

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = isNonLocked

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = isNotDeleted
}