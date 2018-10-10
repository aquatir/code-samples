package codesample.kotlin.graphql.spqr.entitry

import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Cart (
        @Id @GeneratedValue
        val id: Long = 0,

        @OneToMany(fetch = FetchType.EAGER, mappedBy = "cart")
        val cartItems: List<CartItem> = mutableListOf()
) {

    fun getTotal(): BigDecimal {
        return cartItems.stream()
                .map(CartItem::getItemPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
    }
}
