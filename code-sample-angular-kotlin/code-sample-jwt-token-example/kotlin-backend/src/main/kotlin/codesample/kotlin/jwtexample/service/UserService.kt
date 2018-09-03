package codesample.kotlin.jwtexample.service

import codesample.kotlin.jwtexample.security.entity.User
import codesample.kotlin.jwtexample.security.enums.AuthorityName
import codesample.kotlin.jwtexample.security.repository.AuthorityRepository
import codesample.kotlin.jwtexample.security.repository.UserRepository
import codesample.kotlin.jwtexample.security.service.JwtTokenService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService (
        val userRepository: UserRepository,
        val authorityRepository: AuthorityRepository,
        val passwordEncoder: BCryptPasswordEncoder,
        val jwtTokenService: JwtTokenService
) {

    fun createAndSaveUser(username: String,
                          unhashedPassword: String,
                          name: String,
                          authorities: List<AuthorityName>) : User {
        return User(
                username = username,
                password = passwordEncoder.encode(unhashedPassword),
                name = name,
                authorities = authorityRepository.findByNameIn(authorities),
                refreshToken = jwtTokenService.generateRefreshToken(username))

                .apply { userRepository.save(this) }
    }

    fun updateAndReturnRefreshToken(username: String): String {
        val user = userRepository.findByUsername(username) ?: throw UsernameNotFoundException("user $username not found")
        return user
                .also { it.refreshToken = jwtTokenService.generateRefreshToken(it.username) }
                .also { userRepository.save(it) }
                .refreshToken
    }
}