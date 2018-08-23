package codesample.kotlin.jwtexample.security.controller

import codesample.kotlin.jwtexample.security.service.JwtTokenService
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController



@RestController
class SecurityController (val authenticationManager: AuthenticationManager,
                          val jwtTokenService: JwtTokenService){

    /* This request will not be protected by security */
    @PostMapping("/auth")
    fun auth(username: String, password: String) : ResponseEntity<String> {
        val authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        )

        SecurityContextHolder.getContext().authentication = authentication
        val jwt = jwtTokenService.generateToken(authentication)
        return ResponseEntity.ok(jwt)
    }
}