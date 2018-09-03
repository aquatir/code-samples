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
class SecurityController (private val authenticationManager: AuthenticationManager,
                          private val jwtTokenService: JwtTokenService,
                          private val userService: UserService){

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
        val refreshToken = userService.getRefreshTokenByUsername(loginDto.username)
        return AccessAndRefreshTokenResponse(accessToken, refreshToken)
    }

    /**
     * Get new access token using refresh token.
     *
     * Generally speaking refresh token should be stored somewhere (in DB for example) so that you can invalidate this
     * token from backend if needed.
     *
     * There are however a lot of considerations which should be thought about, e.g.
     * 1) What will happen if refresh token get stolen? Do we check that consecutive refreshes come from the same IP? Do we check
     * if user's behavior was suspicious when issuing new access/refresh token? (e.g. if use has tried to call some service
     * 1000 times during 10 second of access token lifetime this may mean, that user is actually a bot)
     * 2) Do we trust access token for actions like "change username" (which will change refresh token) or should be
     * make this actions with n-factor authentication?
     * 3) Do we log important security information like failed logins / refreshes / username change actions, etc?
     * 4) etc.
     *
     * A little info about token refreshes:
     *
     * Image that we have good Alice and evil Bob. Bob has stolen Alice's refresh and access tokens.
     *
     * - If the next refresh is issued by Bob, Alice's refresh request will fail (so we raise an exception).
     * - If the next refresh is issued by Alice, Bob's refresh request will fail (so we raise an exception again).
     *
     * In both cases we can at least know that something fishy is going on.
     */
    @PostMapping("/auth/refresh")
    fun authRefresh(@RequestBody accessTokenRequest: AccessTokenByRefreshTokenRequest) : AccessAndRefreshTokenResponse {

        val username = userService.getUsernameAndCheckRefreshToken(accessTokenRequest.refreshToken)

        val newAccessToken = jwtTokenService.generateAccessToken(username = username)
        val newRefreshToken = userService.updateRefreshTokenAndReturnUpdated(username)
        return AccessAndRefreshTokenResponse(
                accessToken = newAccessToken,
                refreshToken = newRefreshToken
        )
    }
}