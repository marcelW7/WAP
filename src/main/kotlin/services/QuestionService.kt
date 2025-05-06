package web.application.project.services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import web.application.project.model.Question
import web.application.project.model.Tag
import web.application.project.repositories.QuestionRepository
import web.application.project.repositories.TagRepository

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
