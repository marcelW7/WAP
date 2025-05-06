package web.application.project.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import web.application.project.model.Answer

@Repository
interface AnswerRepository : JpaRepository<Answer, Long> {
    fun findByQuestionId(questionId: Long): List<Answer>
    fun findByAuthorId(authorId: Long): List<Answer>
}
