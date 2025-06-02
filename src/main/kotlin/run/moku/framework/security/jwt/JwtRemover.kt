package run.moku.framework.security.jwt

import org.springframework.stereotype.Component
import run.moku.framework.cache.CacheDto
import run.moku.framework.cache.CacheService

@Component
class JwtRemover(
    private val cacheService: CacheService
) {

    fun remove(token: String) = cacheService.save(
        CacheDto(
            key = token,
            value = JwtValues.BLACK_LIST
        )
    )
}