package run.moku.modules.users.adapter.out.infrastructure.jpa.command

import org.springframework.data.repository.Repository
import run.moku.modules.users.adapter.out.infrastructure.jpa.entity.UserJpaEntity

interface UserCommandRepository : Repository<UserJpaEntity, Long> {
    fun save(entity: UserJpaEntity)
}