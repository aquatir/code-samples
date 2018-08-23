package codesample.kotlin.jwtexample.security.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SecurityController {


    @PostMapping("/auth")
    fun auth(username: String, password: String) : String {
        return "kkke"
    }
}