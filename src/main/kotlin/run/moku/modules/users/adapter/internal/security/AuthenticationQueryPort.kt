package run.moku.modules.users.adapter.internal.security

import run.moku.framework.security.auth.AuthenticationDTO

interface AuthenticationQueryPort {
    fun loadUserDetails(username: String): AuthenticationDTO?
}