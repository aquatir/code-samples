package codesample.kotlin.jwtexample.security.model

import codesample.kotlin.jwtexample.security.entity.User
import org.aspectj.weaver.tools.cache.SimpleCacheFactory.enabled
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserWithAuthorities (
        private var username: String,
        private var password: String,
        val name: String,
        private val authorities: Collection<GrantedAuthority>,
        val id: Long
) : UserDetails {

    constructor(user: User) : this (
            user.username,
            user.password,
            user.name,
            user.grantedAuthorities,
            user.id)


    override fun getUsername(): String = username
    override fun getPassword(): String = password
    override fun getAuthorities() = authorities
    override fun isEnabled(): Boolean = enabled

    override fun isCredentialsNonExpired(): Boolean  = true
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true


}