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
        return User(
                username = username,
                password = passwordEncoder.encode(unhashedPassword),
                name = name,
                authorities = authorityRepository.findByNameIn(authorities))

                .apply { userRepository.save(this)
        }
    }
}