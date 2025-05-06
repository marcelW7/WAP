package pl.allegro.product.index.Controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.allegro.product.index.Model.User
import pl.allegro.product.index.Services.UserService

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest): ResponseEntity<UserResponse> {
        val user = userService.createUser(
            email = request.email,
            username = request.username,
            password = request.password
        )
        return ResponseEntity.ok(UserResponse.fromUser(user))
    }

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): ResponseEntity<UserResponse> {
        val user = userService.findById(id)
        return ResponseEntity.ok(UserResponse.fromUser(user))
    }
}

data class RegisterRequest(
    val email: String,
    val username: String,
    val password: String
)

data class UserResponse(
    val id: Long?,
    val email: String,
    val username: String,
    val reputation: Int
) {
    companion object {
        fun fromUser(user: User) = UserResponse(
            id = user.id,
            email = user.email,
            username = user.username,
            reputation = user.reputation
        )
    }
}
