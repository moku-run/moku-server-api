package run.moku.framework.cache

interface CacheService {
    fun save(target: CacheDto, ttl: Long = -1L)
    fun remove(key: String)
    fun get(key: String): String?
}