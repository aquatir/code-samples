package codesample.kotlin.sandbox.springboot.controller

import codesample.kotlin.sandbox.springboot.db.CustomerRepository
import codesample.kotlin.sandbox.springboot.entity.Customer
import org.springframework.web.bind.annotation.*

@RestController
class HelloWorldController (
        val customerRepository: CustomerRepository
) {

    @GetMapping("/")
    fun helloWorld() :String
            = "Hello, world!"

    @GetMapping("/all")
    fun findAll() : List<Customer>
            = customerRepository.findAll()


    @GetMapping("/lastNameBauer")
    fun getBauers() : List<Customer>
            = customerRepository.findByLastName("Bauer")

    @PostMapping("/customer")
    fun getCustomer(@RequestParam name: String) : List<Customer>
            = customerRepository.findByFirstName(name)

}