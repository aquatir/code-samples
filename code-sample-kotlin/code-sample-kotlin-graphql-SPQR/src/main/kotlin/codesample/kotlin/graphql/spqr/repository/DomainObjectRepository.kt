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
            = DomainObject("value # $cartItem.id.toString()", rnd.nextInt(10), rnd.nextLong())

    fun getByCartItemsList(cartItems: List<CartItem>, fields: Set<String>) : List<DomainObject>
        = cartItems.stream()
                .map { it ->
                    DomainObject(
                            if (fields.contains("strValue")) "value # $it.id.toString()" else "",
                            if (fields.contains("intValue")) rnd.nextInt(10) else 0,
                            if (fields.contains("longValue")) rnd.nextLong() else 0)
                }
                .collect(Collectors.toList())

}
