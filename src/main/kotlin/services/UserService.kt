package pl.allegro.product.index.Services

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import pl.allegro.product.index.Model.User
import pl.allegro.product.index.Repositories.UserRepository

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun createUser(email: String, username: String, password: String): User {
        val user = User(
            email = email,
            username = username,
            password = passwordEncoder.encode(password)
        )
        return userRepository.save(user)
    }

    fun findByEmail(email: String): User? = userRepository.findByEmail(email)

    fun findById(id: Long): User = userRepository.findById(id)
        .orElseThrow { Exception("User not found") }
}
