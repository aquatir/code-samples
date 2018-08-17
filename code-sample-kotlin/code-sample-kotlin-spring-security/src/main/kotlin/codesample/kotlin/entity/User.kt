package codesample.kotlin.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


@Entity
class User (
        private var username: String = "",
        private var password: String = "",
        private var enabled: Boolean = true,
        @Id @GeneratedValue
        var id: Long = 0) : UserDetails {

    override fun getAuthorities() : MutableCollection<out GrantedAuthority>
            = AuthorityUtils.NO_AUTHORITIES

    override fun getUsername(): String = username
    override fun getPassword(): String = password
    override fun isEnabled(): Boolean = enabled

    override fun isCredentialsNonExpired(): Boolean  = true
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
}