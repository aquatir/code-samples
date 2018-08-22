package codesample.kotlin.jwtexample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinJwtAppRunner

fun main(args: Array<String>) {
    runApplication<KotlinJwtAppRunner>(*args)
}