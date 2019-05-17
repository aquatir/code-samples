package codesample.kotlin.graphql.repository

import codesample.kotlin.graphql.entitry.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: JpaRepository<Product, Long> {

}