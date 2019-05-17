package codesample.kotlin.graphql.service

import codesample.kotlin.graphql.repository.CartRepository
import org.springframework.stereotype.Service

@Service
class CartService(val cartRepository: CartRepository) {

    fun getCartById(id: Long) = cartRepository.getOne(id)
}