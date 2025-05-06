package web.application.project.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import web.application.project.model.Tag

@Repository
interface TagRepository : JpaRepository<Tag, Long> {
    fun findByName(name: String): Tag?
}
