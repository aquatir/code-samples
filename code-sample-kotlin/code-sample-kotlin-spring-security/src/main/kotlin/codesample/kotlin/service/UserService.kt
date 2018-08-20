package codesample.kotlin.service

import codesample.kotlin.entity.User
import codesample.kotlin.exception.UserExistException
import codesample.kotlin.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class UserService (@Autowired private val userRepository: UserRepository,
                   @Autowired private val passwordEncoder: PasswordEncoder){

    fun getCurrentAuthenticatedUser() : User {
        /* This principle will be obtained from DbUserDetailsService because we implement authenticationProvider
        * and set it as UserDetailsService for auth provider */
        val principal = SecurityContextHolder.getContext().authentication.principal
        return principal as User
    }

    @Transactional
    @Throws(UserExistException::class)
    fun createNewAndSave(username: String, password: String, secondName: String): User {
        val user = userRepository.findByUsername(username)
        if (user != null) {
            throw UserExistException("User $username already exist")
        }

        val encodedPassword = passwordEncoder.encode(password)
        val newUser = User(username = username, password = encodedPassword, secondName = secondName)
        return userRepository.save(newUser)
    }
}