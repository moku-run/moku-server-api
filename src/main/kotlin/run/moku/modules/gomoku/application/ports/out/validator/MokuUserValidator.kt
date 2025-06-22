package run.moku.modules.gomoku.application.ports.out.validator

import run.moku.modules.gomoku.domain.entity.user.MokuUser

interface MokuUserValidator {
    fun valid(mokuUser: MokuUser)
}