package run.moku.modules.users.adapter.out.infrastructure.jpa.query

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.Repository
import org.springframework.data.repository.query.Param
import run.moku.framework.api.exception.ApiException
import run.moku.framework.api.response.ApiResponseCode
import run.moku.modules.users.adapter.out.infrastructure.jpa.entity.UserJpaEntity

interface UserQueryRepository : Repository<UserJpaEntity, Long> {
    fun findById(id: Long): UserJpaEntity?
    fun findByLoginId(loginId: String): UserJpaEntity?
    fun findByNickname(nickname: String): UserJpaEntity?
    fun findAll(): List<UserJpaEntity>

    @Query(
        value = "SELECT EXISTS (SELECT 1 FROM mst_users WHERE nickname = :nickname LIMIT 1)",
        nativeQuery = true
    )
    fun existsByNicknameRaw(@Param("nickname") nickname: String): Int

    @Query(
        value = "SELECT EXISTS (SELECT 1 FROM mst_users WHERE login_id = :loginId LIMIT 1)",
        nativeQuery = true
    )
    fun existsByLoginIdRaw(@Param("loginId") loginId: String): Int
}

fun UserQueryRepository.loadById(id: Long): UserJpaEntity =
    findById(id) ?: throw ApiException(ApiResponseCode.NOT_FOUND_USER)

fun UserQueryRepository.loadByLoginId(loginId: String): UserJpaEntity =
    findByLoginId(loginId) ?: throw ApiException(ApiResponseCode.NOT_FOUND_USER)

fun UserQueryRepository.loadByNickname(nickname: String): UserJpaEntity =
    findByLoginId(nickname) ?: throw ApiException(ApiResponseCode.NOT_FOUND_USER)

fun UserQueryRepository.existsByNickname(nickname: String): Boolean =
    existsByNicknameRaw(nickname) > 0

fun UserQueryRepository.existsByLoginId(loginId: String): Boolean =
    existsByLoginIdRaw(loginId) > 0