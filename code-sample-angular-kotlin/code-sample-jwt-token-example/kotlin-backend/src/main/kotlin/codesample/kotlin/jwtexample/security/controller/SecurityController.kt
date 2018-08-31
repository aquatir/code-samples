package codesample.kotlin.jwtexample.security.controller

import codesample.kotlin.jwtexample.dto.LoginDto
import codesample.kotlin.jwtexample.dto.TokenDto
import codesample.kotlin.jwtexample.security.service.JwtTokenService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class SecurityController (val authenticationManager: AuthenticationManager,
                          val jwtTokenService: JwtTokenService){

    /* This request will not be protected by security */
    @PostMapping("/auth")
    fun auth(@RequestBody loginDto: LoginDto) : TokenDto {
        val authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                        loginDto.username,
                        loginDto.password
                )
        )

        SecurityContextHolder.getContext().authentication = authentication
        val accessToken = jwtTokenService.generateAccessToken(authentication)
        val refreshToken = jwtTokenService.generateRefreshToken(authentication)
        return TokenDto(accessToken, refreshToken)
    }
}