package codesample.kotlin

import codesample.kotlin.entity.User
import codesample.kotlin.repository.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class KotlinSpringBootSecurityRunner {

    @Bean
    fun init(repository: UserRepository) = CommandLineRunner {
        repository.save(User("admin", "admin"))
        repository.save(User("user", "password"))
        repository.save(User("ivan", "narkoman"))
    }
}

fun main(args: Array<String>) {

    runApplication<KotlinSpringBootSecurityRunner>(*args)

}