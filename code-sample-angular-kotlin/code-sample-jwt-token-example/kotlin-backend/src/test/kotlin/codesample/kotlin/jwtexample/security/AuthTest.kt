package codesample.kotlin.jwtexample.security

import codesample.kotlin.jwtexample.security.service.JwtTokenService
import codesample.kotlin.jwtexample.util.TestUtils
import codesample.kotlin.jwtexample.util.TestUtils.Companion.getUrlWithToken
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile
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
        val result = mockMvc.perform(post("/auth")
                .param("username", "admin")
                .param("password", "admin"))
                //.andDo(print())
                .andExpect(status().is2xxSuccessful)
                .andReturn()

        val token = result.response.contentAsString
        Assert.assertTrue("Token header generated incorrectly",
                token.contains("eyJhbGciOiJIUzUxMiJ9"))

        Assert.assertTrue("Token is invalid",
                tokenService.validateToken(token))
    }

    /**
     * Test bad auth is handler with 401 response
     */
    @Test
    fun authTestBad() {
        mockMvc.perform(post("/auth")
                .param("username", "not-a-user")
                .param("password", "not-a-password"))
                .andDo(print())
                .andExpect(status().is4xxClientError)
    }

    /**
     * Test expired token does not work
     */
    @Test
    fun getDataTestWithToken() {
        val goodToken = testUtils.generateTokenForSeconds(1, -10)
        mockMvc.perform(
                getUrlWithToken("/data", goodToken))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError)
    }
}