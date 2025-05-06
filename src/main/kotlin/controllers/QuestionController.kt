package web.application.project.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import web.application.project.model.Question
import web.application.project.services.QuestionService

@RestController
@RequestMapping("/api/questions")
class QuestionController(private val questionService: QuestionService) {

    @PostMapping
    fun createQuestion(@RequestBody request: CreateQuestionRequest): ResponseEntity<QuestionResponse> {
        val question = questionService.createQuestion(
            title = request.title,
            content = request.content,
            authorId = request.authorId,
            tagNames = request.tags
        )
        return ResponseEntity.ok(QuestionResponse.fromQuestion(question))
    }

    @GetMapping("/{id}")
    fun getQuestion(@PathVariable id: Long): ResponseEntity<QuestionResponse> {
        val question = questionService.findById(id)
        return ResponseEntity.ok(QuestionResponse.fromQuestion(question))
    }

    @GetMapping
    fun getAllQuestions(): ResponseEntity<List<QuestionResponse>> {
        val questions = questionService.findAll()
        return ResponseEntity.ok(questions.map { QuestionResponse.fromQuestion(it) })
    }
}

data class CreateQuestionRequest(
    val title: String,
    val content: String,
    val authorId: Long,
    val tags: Set<String>
)

data class QuestionResponse(
    val id: Long?,
    val title: String,
    val content: String,
    val author: UserResponse,
    val tags: Set<String>,
    val createdAt: String
) {
    companion object {
        fun fromQuestion(question: Question) = QuestionResponse(
            id = question.id,
            title = question.title,
            content = question.content,
            author = UserResponse.fromUser(question.author),
            tags = question.tags.map { it.name }.toSet(),
            createdAt = question.createdAt.toString()
        )
    }
}
