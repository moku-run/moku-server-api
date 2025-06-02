package run.moku.framework.cache

import org.springframework.stereotype.Component

@Component
class InMemoryCache : CacheService {

    private data class CacheEntry(
        val value: String,
        val ttl: Long
    )

    override fun save(target: CacheDto, ttl: Long) {
        val expiresAt = if (ttl == PERMANENT_TTL) ttl else System.currentTimeMillis() + ttl

        STORE[target.key] = CacheEntry(value = target.value, ttl = expiresAt)
    }

    override fun remove(key: String) {
        STORE.remove(key)
    }

    override fun get(key: String): String? {
        val entry = STORE[key]
        val now = System.currentTimeMillis()

        if (entry == null || (entry.ttl < now && entry.ttl != PERMANENT_TTL)) {
            STORE.remove(key)
            return null
        }

        return entry.value
    }

    companion object {
        private const val PERMANENT_TTL = -1L

        private val STORE = mutableMapOf<String, CacheEntry>()
    }
}