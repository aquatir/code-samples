package codesample.kotlin.graphql.service

import codesample.kotlin.graphql.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(val productRepository: ProductRepository) {

    fun getById(id: Long) = productRepository.getOne(id)
}