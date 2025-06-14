package run.moku.modules.users.application.ports.out.event

import run.moku.modules.users.domain.entity.UserId

interface UserEventPublisher {
    fun signUpEvent(userId: UserId)
}