package codesample.kotlin.graphql.service

import codesample.kotlin.graphql.repository.CartRepository
import org.springframework.stereotype.Service

@Service
class CartService(val cartRepository: CartRepository) {

    fun getById(id: Long) = cartRepository.getOne(id)
}