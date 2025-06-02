package run.moku.framework.jpa.entity

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.LastModifiedBy
import run.moku.framework.time.TimeUtil.nowKST
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseJpaEntity : Validatable {

    @CreatedBy
    @Column(name = "created_by", nullable = false)
    private var createdBy: Long? = 0L

    @Column(name = "created_at", nullable = false)
    private var createdAt: LocalDateTime = nowKST()

    @LastModifiedBy
    @Column(name = "updated_by", nullable = false)
    private var updatedBy: Long? = 0L

    @Column(name = "updated_at", nullable = false)
    private var updatedAt: LocalDateTime = nowKST()

    @Column(name = "is_deleted", nullable = false)
    private var isDeleted: Boolean = false

    @Column(name = "deleted_at")
    private var deletedAt: LocalDateTime? = null

    @PrePersist
    fun prePersist() {
        this.createdAt = nowKST()
        this.updatedAt = nowKST()
    }

    @PreUpdate
    fun preUpdate() {
        this.updatedAt = nowKST()
    }

    fun softDelete() {
        this.isDeleted = true
        this.deletedAt = nowKST()
    }

    fun restore() {
        this.isDeleted = false
    }
}