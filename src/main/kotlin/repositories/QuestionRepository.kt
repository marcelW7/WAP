package pl.allegro.product.index.Repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.allegro.product.index.Model.Question

@Repository
interface QuestionRepository : JpaRepository<Question, Long> {
    fun findByAuthorId(authorId: Long): List<Question>
    fun findByTagsName(tagName: String): List<Question>
}
