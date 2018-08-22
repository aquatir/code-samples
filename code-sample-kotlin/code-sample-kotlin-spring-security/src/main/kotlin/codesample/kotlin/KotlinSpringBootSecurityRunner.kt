package codesample.kotlin

import codesample.kotlin.repository.UserRepository
import codesample.kotlin.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class KotlinSpringBootSecurityRunner (@Autowired private val userService: UserService) {

    @Bean
    fun init(repository: UserRepository) = CommandLineRunner {
        userService.createNewAndSave("admin", "admin", "adminovich")
        userService.createNewAndSave("user", "password", "userovich")
        userService.createNewAndSave("ivan", "narkoman", "ivanovich")
    }
}

fun main(args: Array<String>) {

    runApplication<KotlinSpringBootSecurityRunner>(*args)

}