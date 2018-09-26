package codesample.kotlin.graphql

import codesample.kotlin.graphql.entitry.Cart
import codesample.kotlin.graphql.entitry.Product
import codesample.kotlin.graphql.repository.CartRepository
import codesample.kotlin.graphql.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.math.BigDecimal


@SpringBootApplication
class KotlinSpringBootGraphqlRunner {

    @Bean
    fun init(
            @Autowired productRepository: ProductRepository,
            @Autowired cartRepository: CartRepository) = CommandLineRunner {

        val products = listOf(
                Product(1, 5, BigDecimal.TEN),
                Product(2, 13, BigDecimal.ONE),
                Product(3, 2, BigDecimal.TEN)
        )
        val products2 = listOf(
                Product(4, 2, BigDecimal.valueOf(3)),
                Product(5, 444, BigDecimal.valueOf(33))
        )

        productRepository.saveAll(products)
        productRepository.saveAll(products2)

        cartRepository.save(Cart(1, products))
        cartRepository.save(Cart(2, products2))
    }
}

fun main(args: Array<String>) {

    runApplication<KotlinSpringBootGraphqlRunner>(*args)

}