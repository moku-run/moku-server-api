package run.moku.modules.gomoku.play.adapter.out.infrastructure.jpa.entity

import jakarta.persistence.*
import run.moku.framework.jpa.entity.BaseJpaEntity

@Entity
@Table(name = "his_gomoku")
class HisGomokuJpaEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "white_stone_player")
    var whiteStonePlayer: Long,

    @Column(name = "black_stone_player")
    var blackStonePlayer: Long,

    @Column(name = "history", columnDefinition = "JSON")
    var history: Any,

    @Column(name = "winner")
    var winner: Long,
) : BaseJpaEntity() {
}