package codesample.kotlin.sandbox.springboot.controller

import codesample.kotlin.sandbox.springboot.db.CustomerRepository
import codesample.kotlin.sandbox.springboot.entity.Customer
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldController (
        val customerRepository: CustomerRepository
) {

    @GetMapping("/")
    fun helloWorld() :String {
        return "Hello, world!"
    }

    @GetMapping("/lastNameBauer")
    fun getBauers() : List<Customer> {
        return customerRepository.findByLastName("Bauer")
    }
}