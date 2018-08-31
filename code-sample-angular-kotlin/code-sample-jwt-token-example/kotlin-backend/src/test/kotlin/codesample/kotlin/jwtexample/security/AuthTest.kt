package codesample.kotlin.jwtexample.security

import codesample.kotlin.jwtexample.dto.LoginDto
import codesample.kotlin.jwtexample.dto.TokenDto
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
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
    lateinit var testUtils: TestUtils

    /**
     * Test auth with good credentials returns a valid token
     */
    @Test
    fun authTestGood() {
        val result = mockMvc.perform(
                post("/auth")
                    .content(asJsonString(LoginDto("admin", "admin")))
                    .contentType(MediaType.APPLICATION_JSON)
                )
                //.andDo(print())
                .andExpect(status().is2xxSuccessful)
                .andReturn()

        val tokenDto = toObject(result.response.contentAsString, TokenDto::class.java)
        Assert.assertTrue("Access token header generated incorrectly",
                tokenDto.accessToken.contains("eyJhbGciOiJIUzUxMiJ9"))

        Assert.assertTrue("Refresh token header generated incorrectly",
                tokenDto.refreshToken.contains("eyJhbGciOiJIUzUxMiJ9"))

        Assert.assertTrue("Access token is invalid",
                tokenService.validateAccessToken(tokenDto.accessToken))

        Assert.assertTrue("Refresh token is invalid",
                tokenService.validateRefreshToken(tokenDto.refreshToken))
    }

    /**
     * Test bad auth is handler with 401 response
     */
    @Test
    fun authTestBad() {
        mockMvc.perform(
                post("/auth")
                        .content(asJsonString(LoginDto("not-a-user", "not-a-password")))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is4xxClientError)
    }

    /**
     * Test expired token does not work
     */
    @Test
    fun getDataTestWithExpiredAccessToken() {
        val goodToken = testUtils.generateAccessTokenForSeconds(1, -10)
        mockMvc.perform(
                getUrlWithToken("/data", goodToken))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError)
    }
}