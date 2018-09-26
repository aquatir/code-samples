package codesample.kotlin.graphql.entitry

import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Product(
        @Id @GeneratedValue val id: Long = 0,
        val quantity: Int = 0,
        val price: BigDecimal = BigDecimal.ZERO
)
