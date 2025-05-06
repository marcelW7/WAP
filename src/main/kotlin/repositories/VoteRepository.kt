package pl.allegro.product.index.Repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.allegro.product.index.Model.Vote

@Repository
interface VoteRepository : JpaRepository<Vote, Long> {
    fun findByUserIdAndQuestionId(userId: Long, questionId: Long): Vote?
    fun findByUserIdAndAnswerId(userId: Long, answerId: Long): Vote?
    fun findByQuestionId(questionId: Long): List<Vote>
    fun findByAnswerId(answerId: Long): List<Vote>
}
