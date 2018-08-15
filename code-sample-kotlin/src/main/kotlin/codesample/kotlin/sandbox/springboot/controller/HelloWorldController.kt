package codesample.kotlin.sandbox.springboot.controller

import codesample.kotlin.sandbox.springboot.db.CustomerRepository
import codesample.kotlin.sandbox.springboot.entity.Customer
import org.springframework.web.bind.annotation.*

@RestController
class HelloWorldController (
        val customerRepository: CustomerRepository
) {

    @GetMapping("/")
    fun helloWorld() :String {
        return "Hello, world!"
    }

    @GetMapping("/all")
    fun findAll() : List<Customer> {
        return customerRepository.findAll()
    }

    @GetMapping("/lastNameBauer")
    fun getBauers() : List<Customer> {
        return customerRepository.findByLastName("Bauer")
    }

    @PostMapping("/customer")
    fun getCustomer(@RequestParam name: String) : List<Customer>  {
        return customerRepository.findByFirstName(name)
    }


}