package web.application.project.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table

@Entity
@Table(name = "tags")
data class Tag(
    @Column(unique = true)
    val name: String,

    val description: String? = null,

    @ManyToMany(mappedBy = "tags")
    val questions: Set<Question> = mutableSetOf()
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}