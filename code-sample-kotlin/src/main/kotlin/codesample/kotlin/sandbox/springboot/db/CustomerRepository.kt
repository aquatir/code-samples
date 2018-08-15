package codesample.kotlin.sandbox.springboot.db

import codesample.kotlin.sandbox.springboot.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CustomerRepository : JpaRepository<Customer, Long> {

    fun findByLastName(lastName: String) : List<Customer>

    @Query(value = "SELECT * FROM Customer c " +
            "WHERE c.name = :name", nativeQuery = true)
    fun findByFirstName(name: String) : List<Customer>
}