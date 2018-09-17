package codesample.kotlin.jwtexample.security

import codesample.kotlin.jwtexample.dto.request.LoginRequest
import codesample.kotlin.jwtexample.dto.request.AccessTokenByRefreshTokenRequest
import codesample.kotlin.jwtexample.dto.response.AccessAndRefreshTokenResponse
import codesample.kotlin.jwtexample.security.repository.UserRepository
import codesample.kotlin.jwtexample.security.service.JwtTokenService
import codesample.kotlin.jwtexample.util.TestUtils
import codesample.kotlin.jwtexample.util.TestUtils.Companion.asJsonString
import codesample.kotlin.jwtexample.util.TestUtils.Companion.getUrlWithToken
import codesample.kotlin.jwtexample.util.TestUtils.Companion.toObject
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class AuthTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var tokenService: JwtTokenService

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var testUtils: TestUtils

    @Test
    fun authWithGoodAccessToken_ExpectSuccessAndTokensAreValid() {
        val result = mockMvc.perform(
                post("/auth")
                    .content(asJsonString(LoginRequest("admin", "admin")))
                    .contentType(MediaType.APPLICATION_JSON)
                )
                //.andDo(print())
                .andExpect(status().is2xxSuccessful)
                .andReturn()

        val tokenDto = toObject(result.response.contentAsString, AccessAndRefreshTokenResponse::class.java)
        Assert.assertTrue("Access token header generated incorrectly",
                tokenDto.accessToken.contains("eyJhbGciOiJIUzUxMiJ9"))

        Assert.assertTrue("Refresh token header generated incorrectly",
                tokenDto.refreshToken.contains("eyJhbGciOiJIUzUxMiJ9"))

        Assert.assertTrue("Access token is invalid",
                tokenService.validateAccessToken(tokenDto.accessToken))

        Assert.assertTrue("Refresh token is invalid",
                tokenService.validateRefreshToken(tokenDto.refreshToken))
    }

    @Test
    fun authWithExpiredAccessToken_ExpectUnauthorized() {
        val goodToken = testUtils.generateAccessTokenForMills("admin", -10)
        mockMvc.perform(
                getUrlWithToken("/data", goodToken))
                //.andDo(print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError)
    }

    @Test
    fun authWithWrongCredentials_ExpectUnauthorized() {
        mockMvc.perform(
                post("/auth")
                        .content(asJsonString(LoginRequest("not-a-user", "not-a-password")))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                //.andDo(print())
                .andExpect(status().is4xxClientError)
    }

    /** Can refresh tokens using valid refresh token on refresh tokens endpoint*/
    @Test
    fun refreshTokensCallWithCorrectRefreshToken_ExpectTokensRefreshedBothValid() {
        val oldGoodRefreshToken = userRepository.findByUsername("admin")!!.refreshToken

        val result = mockMvc.perform(
                post("/auth/refresh")
                        .content(asJsonString(AccessTokenByRefreshTokenRequest(oldGoodRefreshToken)))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                //.andDo(print())
                .andReturn()

        val goodTokens: AccessAndRefreshTokenResponse
                = toObject(result.response.contentAsString, AccessAndRefreshTokenResponse::class.java)

        Assert.assertTrue(tokenService.validateAccessToken(goodTokens.accessToken))
        Assert.assertTrue(tokenService.validateRefreshToken(goodTokens.refreshToken))
        Assert.assertNotEquals(oldGoodRefreshToken, goodTokens.refreshToken)
    }

    @Test
    fun refreshTokensCallWithExpiredRefreshToken_ExpectUnauthorized() {
        val goodRefreshToken = testUtils.generateRefreshTokenForMills("admin", -10)

        mockMvc.perform(
                post("/auth/refresh")
                        .content(asJsonString(AccessTokenByRefreshTokenRequest(goodRefreshToken)))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                //.andDo(print())
                .andExpect(status().isUnauthorized)
    }
}