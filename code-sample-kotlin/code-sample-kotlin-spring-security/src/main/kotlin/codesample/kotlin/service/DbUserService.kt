package codesample.kotlin.service

import codesample.kotlin.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class DbUserService (@Autowired private val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails
        = userRepository.findByUsername(username) ?: throw UsernameNotFoundException("user $username not found")

    fun allUsers() = userRepository.findAll()
}