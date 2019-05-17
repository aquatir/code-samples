package codesample.kotlin.graphql.entitry

import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Cart (
        @Id @GeneratedValue
        var id: Long = 0,

        @OneToMany(fetch = FetchType.EAGER, mappedBy = "cart")
        var cartItems: List<CartItem> = mutableListOf()
) {

    fun getTotal(): BigDecimal {
        return cartItems.stream()
                .map(CartItem::getItemPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
    }
}
