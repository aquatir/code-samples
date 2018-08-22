package codesample.kotlin.jwtexample.security.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.stream.Collectors
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Table

@Entity
@Table(name = "USER")
class User (
        @Column(name = "USERNAME", length = 50, unique = true, nullable = false)
        val username: String,

        @Column(name = "PASSWORD", length = 100, nullable = false)
        val password: String,

        @Column(name = "NAME", length = 100)
        val name: String,

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(
                name = "USER_AUTHORITY",
                joinColumns = [JoinColumn(name = "USER_ID", referencedColumnName = "ID")],
                inverseJoinColumns = [JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")])
        val authorities: Set<Authority> = emptySet(),

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "ID")
        var id: Long = 0
) {


        val grantedAuthorities: Collection<GrantedAuthority>
                get() {
                        return authorities.stream()
                                .map { it -> SimpleGrantedAuthority(it.name.name) }
                                .collect(Collectors.toList())
                }
}