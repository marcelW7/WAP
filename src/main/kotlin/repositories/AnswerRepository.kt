package pl.allegro.product.index.Repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.allegro.product.index.Model.Answer

@Repository
interface AnswerRepository : JpaRepository<Answer, Long> {
    fun findByQuestionId(questionId: Long): List<Answer>
    fun findByAuthorId(authorId: Long): List<Answer>
}
