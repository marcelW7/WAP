package pl.allegro.product.index.Services

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import pl.allegro.product.index.Model.Answer
import pl.allegro.product.index.Repositories.AnswerRepository

@Service
class AnswerService(
    private val answerRepository: AnswerRepository,
    private val questionService: QuestionService,
    private val userService: UserService
) {
    @Transactional
    fun createAnswer(
        content: String,
        authorId: Long,
        questionId: Long
    ): Answer {
        val author = userService.findById(authorId)
        val question = questionService.findById(questionId)

        val answer = Answer(
            content = content,
            author = author,
            question = question
        )

        return answerRepository.save(answer)
    }

    fun findById(id: Long): Answer = answerRepository.findById(id)
        .orElseThrow { Exception("Answer not found") }

    fun findAnswersForQuestion(questionId: Long): List<Answer> =
        answerRepository.findByQuestionId(questionId)
}
