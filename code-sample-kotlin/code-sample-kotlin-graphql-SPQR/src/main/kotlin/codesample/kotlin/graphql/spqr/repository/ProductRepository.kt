package codesample.kotlin.graphql.spqr.repository

import codesample.kotlin.graphql.spqr.entitry.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: JpaRepository<Product, Long> {

}