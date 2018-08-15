package codesample.kotlin.sandbox.springboot

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class SpringBootRunner

fun main(args: Array<String>) {
    SpringApplication.run(SpringBootRunner::class.java, *args)
}