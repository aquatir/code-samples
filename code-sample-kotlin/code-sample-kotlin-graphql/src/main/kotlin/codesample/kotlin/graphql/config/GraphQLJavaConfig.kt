package codesample.kotlin.graphql.config

import codesample.kotlin.graphql.domain.DomainObject
import codesample.kotlin.graphql.entitry.Cart
import codesample.kotlin.graphql.entitry.CartItem
import codesample.kotlin.graphql.service.CartService
import codesample.kotlin.graphql.service.ProductService
import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.coxautodev.graphql.tools.GraphQLResolver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.stream.Collectors

@Configuration
class GraphQLJavaConfig(
        val cartService: CartService,
        val productService: ProductService
) {

    /** This is an entry point for all our queries. This means that all queries should start with cart.
     * You can add other entry points here
     */
    @Bean
    fun queryResolver() = object: GraphQLQueryResolver {
            fun cart(id: Long): Cart = cartService.getCartById(id)
    }

    /**
     * We are allowing a custom property to be queried from CardItem. This property does not exist in CardItem class,
     * but a method to query it DOES exist
     */
    @Bean
    fun cartItemResolver() = object : GraphQLResolver<CartItem> {
        fun domainObject(cartItem: CartItem): DomainObject {
            return DomainObject("object # $cartItem.id")
        }
    }

    @Bean
    fun cartResolver() = object : GraphQLResolver<Cart> {
        fun cartItems(cart: Cart, limit: Int) : List<CartItem> = cart.cartItems.subList(
                0, if (limit > 0) limit else cart.cartItems.size
        )
    }
}