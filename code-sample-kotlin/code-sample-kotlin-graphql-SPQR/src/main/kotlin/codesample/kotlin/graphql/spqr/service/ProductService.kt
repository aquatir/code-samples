package codesample.kotlin.graphql.spqr.service

import codesample.kotlin.graphql.spqr.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(val productRepository: ProductRepository) {

    fun getById(id: Long) = productRepository.getOne(id)
}