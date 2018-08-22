package codesample.kotlin.jwtexample.security.repository

import codesample.kotlin.jwtexample.security.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String) : User?
}