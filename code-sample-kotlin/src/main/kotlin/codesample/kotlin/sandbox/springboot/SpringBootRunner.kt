package codesample.kotlin.sandbox.springboot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootRunner

fun main(args: Array<String>) {

    /**
     * Kotlin's way for classical spring boot
     * SpringApplication.run(SpringBootRunner::class.java, *args)
     */
    runApplication<SpringBootRunner>(*args)

}