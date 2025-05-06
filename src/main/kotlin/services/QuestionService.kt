package pl.allegro.product.index.Services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pl.allegro.product.index.Model.Question
import pl.allegro.product.index.Model.Tag
import pl.allegro.product.index.Repositories.QuestionRepository
import pl.allegro.product.index.Repositories.TagRepository

@Service
class QuestionService(
    private val questionRepository: QuestionRepository,
    private val tagRepository: TagRepository,
    private val userService: UserService
) {
    @Transactional
    fun createQuestion(
        title: String,
        content: String,
        authorId: Long,
        tagNames: Set<String>
    ): Question {
        val author = userService.findById(authorId)
        val tags = tagNames.map { name ->
            tagRepository.findByName(name) ?: tagRepository.save(Tag(name = name))
        }.toSet()

        val question = Question(
            title = title,
            content = content,
            author = author,
            tags = tags
        )

        return questionRepository.save(question)
    }

    fun findById(id: Long): Question = questionRepository.findById(id)
        .orElseThrow { Exception("Question not found") }

    fun findAll(): List<Question> = questionRepository.findAll()
}
