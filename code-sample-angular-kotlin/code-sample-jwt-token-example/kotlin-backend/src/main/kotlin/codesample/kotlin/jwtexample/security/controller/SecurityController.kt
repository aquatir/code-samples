package codesample.kotlin.jwtexample.security.controller

import codesample.kotlin.jwtexample.dto.request.LoginRequest
import codesample.kotlin.jwtexample.dto.request.AccessTokenByRefreshTokenRequest
import codesample.kotlin.jwtexample.dto.response.AccessAndRefreshTokenResponse
import codesample.kotlin.jwtexample.security.service.JwtTokenService
import codesample.kotlin.jwtexample.service.UserService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class SecurityController (val authenticationManager: AuthenticationManager,
                          val jwtTokenService: JwtTokenService,
                          val userService: UserService){

    /* This request will not be protected by security */
    @PostMapping("/auth")
    fun auth(@RequestBody loginDto: LoginRequest) : AccessAndRefreshTokenResponse {
        val authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                        loginDto.username,
                        loginDto.password
                )
        )

        SecurityContextHolder.getContext().authentication = authentication
        val accessToken = jwtTokenService.generateAccessToken(authentication)
        val refreshToken = jwtTokenService.generateRefreshToken(authentication)
        return AccessAndRefreshTokenResponse(accessToken, refreshToken)
    }

    /**
     * Get new access token using refresh token.
     *
     * Generally speaking refresh token should be stored somewhere (in DB for example?) so that you can invalidate this
     * token from backend if needed.
     */
    @PostMapping("/auth/refresh")
    fun authRefresh(@RequestBody accessTokenRequest: AccessTokenByRefreshTokenRequest) : AccessAndRefreshTokenResponse {
        jwtTokenService.validateRefreshToken(accessTokenRequest.refreshToken)
        val username = jwtTokenService.getUsernameFromRefreshJWT(accessTokenRequest.refreshToken)

        val newAccessToken = jwtTokenService.generateAccessToken(username = username)
        val newRefreshToken = userService.updateAndReturnRefreshToken(username)
        return AccessAndRefreshTokenResponse(
                accessToken = newAccessToken,
                refreshToken = newRefreshToken
        )
    }
}