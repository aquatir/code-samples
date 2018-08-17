package codesample.kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinSpringBootSecurityRunner

fun main(args: Array<String>) {

    runApplication<KotlinSpringBootSecurityRunner>(*args)

}