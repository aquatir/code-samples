package codesample.kotlin.graphql.spqr.entitry

import codesample.kotlin.graphql.spqr.domain.DomainObject
import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToOne

@Entity
class CartItem (
        @Id @GeneratedValue
        val id: Long = 0,

        @OneToOne(fetch = FetchType.EAGER)
        val cart: Cart = Cart(),

        @Column(nullable = false)
        val quantity: Int = 0,

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
        val product: Product = Product()
) {

    fun getItemPrice(): BigDecimal = product.price.multiply(BigDecimal(quantity))
}