package codesample.kotlin.graphql.spqr.config

import codesample.kotlin.graphql.spqr.domain.DomainObject
import codesample.kotlin.graphql.spqr.entitry.Cart
import codesample.kotlin.graphql.spqr.entitry.CartItem
import codesample.kotlin.graphql.spqr.repository.DomainObjectRepository
import codesample.kotlin.graphql.spqr.service.CartService
import graphql.execution.batched.Batched
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLContext
import io.leangen.graphql.annotations.GraphQLEnvironment
import io.leangen.graphql.annotations.GraphQLQuery
import org.springframework.context.annotation.Configuration

@Configuration
class CartGraph(val cartService: CartService,
                val domainObjectRepository: DomainObjectRepository) {

    /** This is an entry point for all our queries SPQR. Notice that @GraphQLQuery annotation does not have a name
     * attribute. This defines top-level query.
     */
    @GraphQLQuery
    fun cart(@GraphQLArgument(name = "id") id: Long) = cartService.getCartById(id)


    /**
     * After we have queried the cart, we can continue and query this cart's items.
     * So we specify Cart as our GraphQLContext and also apply limit to it with default value
     * the save way we did when we were configuring graphql with schema file
     */
    @GraphQLQuery(name = "cart")
    fun cartItems(@GraphQLContext cart: Cart,
                  @GraphQLArgument(name = "limit", defaultValue = "0") limit: Int) : List<CartItem>
            = cart.cartItems.subList(0, if (limit > 0) limit else cart.cartItems.size)

    /** After getting to cartItem level we can query DomainObject's thus we
     * have @GraphQLContext and @GrapQLQuery annotations targeting cartItem
     *
     * We use this query as batched, which means that when graphQL have to get DomainObject for
     * more then one CardItem, it will use this single query instead of multiple queries
     *
     * We also going to pass field which were queried to repository, because we don't want all the field to be returned
     * other the "network" (Note: graphql will never show all fields to client, HOWEVER they will still go other the network
     * if this function was going over the net)
     */
    @GraphQLQuery(name = "cartItem")
    @Batched
    fun domainObjects(@GraphQLContext cartItems: List<CartItem>,
                      @GraphQLEnvironment fields: Set<String>): List<DomainObject>
        = domainObjectRepository.getByCartItemsList(cartItems, fields)

}



