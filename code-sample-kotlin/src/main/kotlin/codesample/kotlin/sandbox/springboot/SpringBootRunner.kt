package codesample.kotlin.sandbox.springboot

import codesample.kotlin.sandbox.springboot.db.CustomerRepository
import codesample.kotlin.sandbox.springboot.entity.Customer
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class SpringBootRunner {

    @Bean
    /* The same can be done by placing data.sql file in resources (or defining what this name
    * should be in config) */
    fun init(repository: CustomerRepository) = CommandLineRunner {
        repository.save(Customer("Jack", "Bauer"))
        repository.save(Customer("Chloe", "O'Brian"))
        repository.save(Customer("Kim", "Bauer"))
        repository.save(Customer("David", "Palmer"))
        repository.save(Customer("Michelle", "Dessler"))
    }
}

fun main(args: Array<String>) {

    /**
     * Kotlin's way for classical spring boot
     * SpringApplication.run(SpringBootRunner::class.java, *args)
     */
    runApplication<SpringBootRunner>(*args)

}