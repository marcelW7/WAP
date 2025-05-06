package pl.allegro.product.index.Model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "votes")
data class Vote(
    @ManyToOne(fetch = FetchType.LAZY)
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    val question: Question? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    val answer: Answer? = null,

    val voteType: VoteType,

    val createdAt: LocalDateTime = LocalDateTime.now()
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}

enum class VoteType {
    UP, DOWN
}
