package codesample.kotlin.jwtexample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinSpringBootSecurityRunner

fun main(args: Array<String>) {
    runApplication<KotlinSpringBootSecurityRunner>(*args)
}