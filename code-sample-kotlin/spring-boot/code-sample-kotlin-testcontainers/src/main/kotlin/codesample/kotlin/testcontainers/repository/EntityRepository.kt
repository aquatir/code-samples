package codesample.kotlin.testcontainers.repository

import codesample.kotlin.testcontainers.entity.Entity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EntityRepository : JpaRepository<Entity, UUID>