package codesample.kotlin.jwtexample.service

import codesample.kotlin.jwtexample.security.entity.User
import codesample.kotlin.jwtexample.security.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService (
        val userRepository: UserRepository,
        val passwordEncoder: BCryptPasswordEncoder) {

    fun createAndSaveUser(username: String, unhashedPassword: String, name: String) : User {
        val user = User(username, passwordEncoder.encode(unhashedPassword), name)
        return userRepository.save(user)
    }
}