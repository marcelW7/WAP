package web.application.project.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import web.application.project.model.Answer
import web.application.project.services.AnswerService

@RestController
@RequestMapping("/api/answers")
class AnswerController(private val answerService: AnswerService) {
    @PostMapping
    fun createAnswer(
        @RequestParam questionId: Long,
        @RequestParam authorId: Long,
        @RequestBody content: String
    ): Answer {
        return answerService.createAnswer(content, authorId, questionId)
    }

    @GetMapping("/question/{questionId}")
    fun getAnswersForQuestion(@PathVariable questionId: Long): List<Answer> {
        return answerService.findAnswersForQuestion(questionId)
    }
}
