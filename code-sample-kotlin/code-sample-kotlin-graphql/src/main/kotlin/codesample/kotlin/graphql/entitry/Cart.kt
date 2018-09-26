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

        @OneToMany(fetch = FetchType.EAGER)
        var products: List<Product> = mutableListOf()
) {

    fun getSubTotal(products: List<Product>): BigDecimal {
        return products.stream()
                .map(Product::price)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
    }
}
