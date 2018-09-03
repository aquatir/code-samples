package codesample.kotlin.jwtexample.security.repository

import codesample.kotlin.jwtexample.security.entity.Authority
import codesample.kotlin.jwtexample.security.enums.AuthorityName
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorityRepository : JpaRepository<Authority, Long> {
    fun findByName(name: String) : Authority
    fun findByNameIn(authorityNames: List<AuthorityName>) : List<Authority>
}