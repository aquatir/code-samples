package codesample.kotlin.graphql.entitry

import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany

@Entity
class Cart (
        @Id @GeneratedValue
        var id: Long = 0,

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "CART_PRODUCTS",
                joinColumns = [JoinColumn(name = "CART_ID")],
                inverseJoinColumns = [JoinColumn(name = "PRODUCT_ID")])
        var products: List<Product> = mutableListOf()
) {

    fun getSubTotal(): BigDecimal {
        return products.stream()
                .map(Product::price)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
    }
}
