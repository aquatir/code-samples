package codesample.kotlin.graphql.config

import codesample.kotlin.graphql.entitry.Cart
import codesample.kotlin.graphql.service.CartService
import codesample.kotlin.graphql.service.ProductService
import com.coxautodev.graphql.tools.GraphQLQueryResolver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GraphQLJavaConfig(
        val cartService: CartService,
        val productService: ProductService
) {

    @Bean
    fun queryResolver() = object: GraphQLQueryResolver {
            fun cart(id: Long): Cart = cartService.getById(id)
    }
}