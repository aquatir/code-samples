package codesample.kotlin.jwtexample.security.entity

import codesample.kotlin.jwtexample.security.enums.AuthorityName
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToMany
import javax.persistence.Table

@Entity
@Table(name = "AUTHORITY")
class Authority (

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "ID")
        var id: Long,

        @Column(name = "NAME", length = 50, unique = true, nullable = false)
        @Enumerated(EnumType.STRING)
        val name: AuthorityName,

        @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
        val users: Set<User>
)
