package codesample.kotlin.jwtexample

import codesample.kotlin.jwtexample.security.enums.AuthorityName
import codesample.kotlin.jwtexample.service.UserService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class KotlinJwtAppRunner {

    @Bean
    fun init(userService: UserService) = CommandLineRunner {
        userService.createAndSaveUser("user", "user", "USER", listOf(AuthorityName.ROLE_USER))
        userService.createAndSaveUser("admin", "admin", "ADMIN", listOf(AuthorityName.ROLE_ADMIN))
    }
}

fun main(args: Array<String>) {
    runApplication<KotlinJwtAppRunner>(*args)
}