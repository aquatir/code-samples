package codesample.kotlin.testcontainers.entity

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "Entity", schema = "other")
class Entity (

    @Id
    @Column(name = "uuid", nullable = false, unique = true)
    val uuid: UUID

)