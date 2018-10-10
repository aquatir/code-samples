package codesample.kotlin.graphql.spqr.service

import codesample.kotlin.graphql.spqr.repository.CartRepository
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotation.GraphQLApi
import org.springframework.stereotype.Service
@Service
class CartService(val cartRepository: CartRepository) {

    fun getCartById(id: Long) = cartRepository.getOne(id)
}