package web.application.project.services

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import web.application.project.model.Vote
import web.application.project.model.VoteType
import web.application.project.repositories.VoteRepository

@Service
class VoteService(
    private val voteRepository: VoteRepository,
    private val userService: UserService,
    private val questionService: QuestionService,
    private val answerService: AnswerService
) {
    @Transactional
    fun voteForQuestion(userId: Long, questionId: Long, voteType: VoteType): Vote {
        val user = userService.findById(userId)
        val question = questionService.findById(questionId)

        // Sprawdź, czy użytkownik już głosował
        val existingVote = voteRepository.findByUserIdAndQuestionId(userId, questionId)
        if (existingVote != null) {
            if (existingVote.voteType == voteType) {
                // Jeśli to samo głosowanie - usuń głos
                voteRepository.delete(existingVote)
                return existingVote
            } else {
                // Jeśli przeciwne głosowanie - zaktualizuj typ
                return voteRepository.save(existingVote.copy(voteType = voteType))
            }
        }

        // Nowy głos
        val vote = Vote(
            user = user,
            question = question,
            voteType = voteType
        )

        return voteRepository.save(vote)
    }

    @Transactional
    fun voteForAnswer(userId: Long, answerId: Long, voteType: VoteType): Vote {
        val user = userService.findById(userId)
        val answer = answerService.findById(answerId)

        // Sprawdź, czy użytkownik już głosował
        val existingVote = voteRepository.findByUserIdAndAnswerId(userId, answerId)
        if (existingVote != null) {
            if (existingVote.voteType == voteType) {
                // Jeśli to samo głosowanie - usuń głos
                voteRepository.delete(existingVote)
                return existingVote
            } else {
                // Jeśli przeciwne głosowanie - zaktualizuj typ
                return voteRepository.save(existingVote.copy(voteType = voteType))
            }
        }

        // Nowy głos
        val vote = Vote(
            user = user,
            answer = answer,
            voteType = voteType
        )

        return voteRepository.save(vote)
    }

    fun getQuestionVotesCount(questionId: Long): Map<String, Int> {
        val votes = voteRepository.findByQuestionId(questionId)
        return votes.groupBy { it.voteType }
            .mapValues { it.value.size }
            .let { voteMap ->
                mapOf(
                    "upVotes" to (voteMap[VoteType.UP] ?: 0),
                    "downVotes" to (voteMap[VoteType.DOWN] ?: 0)
                )
            }
    }

    fun getAnswerVotesCount(answerId: Long): Map<String, Int> {
        val votes = voteRepository.findByAnswerId(answerId)
        return votes.groupBy { it.voteType }
            .mapValues { it.value.size }
            .let { voteMap ->
                mapOf(
                    "upVotes" to (voteMap[VoteType.UP] ?: 0),
                    "downVotes" to (voteMap[VoteType.DOWN] ?: 0)
                )
            }
    }
}
