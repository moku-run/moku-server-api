package run.moku.modules.users.application.ports.input.query

import run.moku.framework.security.auth.AuthenticationDTO

interface AuthenticationQueryInput {
    fun loadUserDetails(username: String): AuthenticationDTO
}