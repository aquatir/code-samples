package codesample.kotlin.jwtexample.service

import codesample.kotlin.jwtexample.exceptions.UserRefreshTokenInvalid
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
        private val userRepository: UserRepository,
        private val authorityRepository: AuthorityRepository,
        private val passwordEncoder: BCryptPasswordEncoder,
        private val jwtTokenService: JwtTokenService
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

    fun updateRefreshTokenAndReturnUpdated(username: String): String {
        val user = userRepository.findByUsername(username) ?: throw UsernameNotFoundException("user $username not found")
        return user
                .also { it.refreshToken = jwtTokenService.generateRefreshToken(it.username) }
                .also { userRepository.save(it) }
                .refreshToken
    }

    fun getRefreshTokenByUsername(username: String): String {
        val user = userRepository.findByUsername(username) ?: throw UsernameNotFoundException("user $username not found")
        return user.refreshToken
    }

    fun getUsernameAndCheckRefreshToken(refreshToken: String) : String {
        /* Check refresh token signature */
        jwtTokenService.validateRefreshToken(refreshToken)

        /* Than check that this exact refresh token is stored in DB. If not, this may mean that
        * the refresh token was stolen */
        val username = jwtTokenService.getUsernameFromRefreshJWT(refreshToken)
        val user = userRepository.findByUsername(username) ?: throw UsernameNotFoundException("user $username not found")
        if (user.refreshToken != refreshToken){
            throw UserRefreshTokenInvalid("Refresh Token does not match for user $username!")
        }
        return user.username
    }
}