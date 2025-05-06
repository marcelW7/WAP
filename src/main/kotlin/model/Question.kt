package web.application.project.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "questions")
data class Question(
    val title: String,

    @Column(columnDefinition = "TEXT")
    val content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    val author: User,

    @ManyToMany
    @JoinTable(
        name = "question_tags",
        joinColumns = [JoinColumn(name = "question_id")],
        inverseJoinColumns = [JoinColumn(name = "tag_id")]
    )
    val tags: Set<Tag> = mutableSetOf(),

    @OneToMany(mappedBy = "question", cascade = [CascadeType.ALL])
    val answers: List<Answer> = mutableListOf(),

    @OneToMany(mappedBy = "question", cascade = [CascadeType.ALL])
    val votes: List<Vote> = mutableListOf(),

    val createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now()
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}