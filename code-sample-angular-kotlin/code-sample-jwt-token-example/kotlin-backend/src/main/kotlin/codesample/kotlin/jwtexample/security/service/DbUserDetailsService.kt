package codesample.kotlin.jwtexample.security.service

import codesample.kotlin.jwtexample.security.model.UserWithAuthorities
import codesample.kotlin.jwtexample.security.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class DbUserDetailsService (@Autowired private val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username) ?: throw UsernameNotFoundException("user $username not found")
        return UserWithAuthorities(user)
    }
}