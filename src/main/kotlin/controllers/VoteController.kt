package pl.allegro.product.index.Controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import pl.allegro.product.index.Model.Vote
import pl.allegro.product.index.Model.VoteType
import pl.allegro.product.index.Services.VoteService

@RestController
@RequestMapping("/api/votes")
class VoteController(private val voteService: VoteService) {
    @PostMapping("/question/{questionId}")
    fun voteForQuestion(
        @PathVariable questionId: Long,
        @RequestParam userId: Long,
        @RequestParam voteType: VoteType
    ): Vote {
        return voteService.voteForQuestion(userId, questionId, voteType)
    }

    @PostMapping("/answer/{answerId}")
    fun voteForAnswer(
        @PathVariable answerId: Long,
        @RequestParam userId: Long,
        @RequestParam voteType: VoteType
    ): Vote {
        return voteService.voteForAnswer(userId, answerId, voteType)
    }

    @GetMapping("/question/{questionId}/count")
    fun getQuestionVotesCount(@PathVariable questionId: Long): Map<String, Int> {
        return voteService.getQuestionVotesCount(questionId)
    }

    @GetMapping("/answer/{answerId}/count")
    fun getAnswerVotesCount(@PathVariable answerId: Long): Map<String, Int> {
        return voteService.getAnswerVotesCount(answerId)
    }
}
