package codesample.kotlin.graphql.spqr.repository

import codesample.kotlin.graphql.spqr.entitry.Cart
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CartRepository: JpaRepository<Cart, Long> {

}
