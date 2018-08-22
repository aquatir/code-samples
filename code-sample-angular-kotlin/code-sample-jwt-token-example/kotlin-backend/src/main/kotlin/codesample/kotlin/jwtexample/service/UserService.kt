package codesample.kotlin.jwtexample.service

import codesample.kotlin.jwtexample.security.entity.User
import codesample.kotlin.jwtexample.security.enums.AuthorityName
import codesample.kotlin.jwtexample.security.repository.AuthorityRepository
import codesample.kotlin.jwtexample.security.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService (
        val userRepository: UserRepository,
        val authorityRepository: AuthorityRepository,
        val passwordEncoder: BCryptPasswordEncoder
) {

    fun createAndSaveUser(username: String,
                          unhashedPassword: String,
                          name: String,
                          authorities: List<AuthorityName>) : User {
        val user = User(
                username,
                passwordEncoder.encode(unhashedPassword),
                name,
                authorityRepository.findByNameIn(authorities)
        )
        return userRepository.save(user)
    }
}