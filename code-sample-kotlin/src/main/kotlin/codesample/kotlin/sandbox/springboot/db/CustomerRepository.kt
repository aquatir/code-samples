package codesample.kotlin.sandbox.springboot.db

import codesample.kotlin.sandbox.springboot.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<Customer, Long> {
    fun findByLastName(name: String) : List<String>
}