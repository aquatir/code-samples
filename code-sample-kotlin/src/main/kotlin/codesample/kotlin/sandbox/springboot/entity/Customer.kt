package codesample.kotlin.sandbox.springboot.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Customer (
        var firstName: String = "",
        var lastName: String = "",
        @Id @GeneratedValue var id: Long = 0
)

