package web.application.project.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import web.application.project.model.Question

@Repository
interface QuestionRepository : JpaRepository<Question, Long> {
    fun findByAuthorId(authorId: Long): List<Question>
    fun findByTagsName(tagName: String): List<Question>
}
