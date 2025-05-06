package web.application.project.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import web.application.project.model.Vote

@Repository
interface VoteRepository : JpaRepository<Vote, Long> {
    fun findByUserIdAndQuestionId(userId: Long, questionId: Long): Vote?
    fun findByUserIdAndAnswerId(userId: Long, answerId: Long): Vote?
    fun findByQuestionId(questionId: Long): List<Vote>
    fun findByAnswerId(answerId: Long): List<Vote>
}
