package codesample.kotlin.graphql.spqr.service

import codesample.kotlin.graphql.spqr.repository.CartRepository
import org.springframework.stereotype.Service

@Service
class CartService(val cartRepository: CartRepository) {

    fun getCartById(id: Long) = cartRepository.getOne(id)
}