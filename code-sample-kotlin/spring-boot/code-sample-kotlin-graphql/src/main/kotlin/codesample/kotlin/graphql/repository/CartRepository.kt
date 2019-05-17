package codesample.kotlin.graphql.repository

import codesample.kotlin.graphql.entitry.Cart
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CartRepository: JpaRepository<Cart, Long> {

}
