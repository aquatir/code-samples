package codesample.kotlin.jwtexample

import codesample.kotlin.jwtexample.service.UserService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class KotlinJwtAppRunner {

    @Bean
    fun init(userService: UserService) = CommandLineRunner {
        userService.createAndSaveUser("admin", "admin", "ADMINCHIK")
        userService.createAndSaveUser("user", "password", "USERCHICK")
        userService.createAndSaveUser("ivan", "narkoman", "IVANCHICK")
    }
}

fun main(args: Array<String>) {
    runApplication<KotlinJwtAppRunner>(*args)
}