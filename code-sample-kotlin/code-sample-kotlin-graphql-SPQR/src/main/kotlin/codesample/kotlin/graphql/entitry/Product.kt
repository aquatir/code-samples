package codesample.kotlin.graphql.entitry

import codesample.kotlin.graphql.embeddable.Picture
import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Product(
        @Id @GeneratedValue
        val id: Long = 0,

        @Column(nullable = false)
        val name: String = "",

        @Column(nullable = false)
        val totalQuantity: Int = 0,

        @Column(nullable = false)
        val price: BigDecimal = BigDecimal.ZERO,

        @Column @Embedded
        val picture: Picture = Picture()
)
