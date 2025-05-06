package pl.allegro.product.index.Model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "users")
data class User(
    @Column(unique = true)
    val email: String,

    val username: String,
    val password: String,
    val reputation: Int = 0,
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @OneToMany(mappedBy = "author")
    val questions: List<Question> = mutableListOf(),

    @OneToMany(mappedBy = "author")
    val answers: List<Answer> = mutableListOf()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}