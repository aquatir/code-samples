package codesample.kotlin.graphql.spqr.repository

import codesample.kotlin.graphql.spqr.domain.DomainObject
import codesample.kotlin.graphql.spqr.entitry.CartItem
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

/**
 * This service can be your HTTP client which get data from remote service
 */
@Service
class DomainObjectRepository {
    private val rnd = Random(0)

    fun getByCartItem(cartItem: CartItem): DomainObject
            = DomainObject(
            "id : ${cartItem.id}",
            cartItem.quantity,
            cartItem.id)

    fun getByCartItemsList(cartItems: List<CartItem>) : List<DomainObject>
        = cartItems.stream()
            .map { it -> DomainObject(
                    "id : ${it.id}",
                    it.quantity,
                    it.id) }
            .collect(Collectors.toList())


}
