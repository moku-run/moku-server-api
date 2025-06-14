package run.moku.modules.gomoku.adapter.out.infrastructure.jpa.entity

import jakarta.persistence.*
import run.moku.framework.jpa.entity.BaseJpaEntity
import run.moku.modules.gomoku.application.usecase.stats.query.FetchStatsModel
import run.moku.modules.gomoku.domain.entity.user.MokuUser

@Entity
@Table(name = "his_gomoku_stats")
class HisGomokuStatsJpaEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "user_id")
    var userId: Long,

    @Column(name = "win_count")
    var winCount: Int = 0,

    @Column(name = "lose_count")
    var loseCount: Int = 0,
) : BaseJpaEntity() {

    fun convertModel(): FetchStatsModel =
        FetchStatsModel(
            winCount = winCount,
            loseCount = loseCount,
        )

    companion object {
        fun init(mokuUser: MokuUser): HisGomokuStatsJpaEntity =
            HisGomokuStatsJpaEntity(
                userId = mokuUser.value
            )
    }
}