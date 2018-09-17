package codesample.kotlin.jwtexample.controller

import codesample.kotlin.jwtexample.util.TestUtils
import codesample.kotlin.jwtexample.util.TestUtils.Companion.getUrlWithToken
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class ImportantDataControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var testUtils : TestUtils

    private val TOKEN_TIMEOUT_MS = 10000L
    private val USER_WITH_ROLE_USER = "user"
    private val USER_WITH_ROLE_ADMIN = "admin"

    @Test
    fun getDataWithoutAuthentication_ExpectUnauthorized() {
        mockMvc.perform(get("/data"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isUnauthorized)
    }

    @Test
    fun getDataWithTokenAuthentication_ExpectSuccess() {
        val goodToken = testUtils.generateAccessTokenForMills(USER_WITH_ROLE_USER, TOKEN_TIMEOUT_MS)
        mockMvc.perform(
                getUrlWithToken("/data", goodToken))
                //.andDo(print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
    }

    @Test
    fun getUserDataWithUserAuthenticatedWithRoleUser_ExpectSuccess() {
        val goodToken = testUtils.generateAccessTokenForMills(USER_WITH_ROLE_USER, TOKEN_TIMEOUT_MS)
        mockMvc.perform(
                getUrlWithToken("/userData", goodToken))
                //.andDo(print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
    }

    @Test
    fun getUserDataWithUserAuthenticatedWithRoleUser_ExpectUnauthorized() {
        val goodToken = testUtils.generateAccessTokenForMills(USER_WITH_ROLE_ADMIN, TOKEN_TIMEOUT_MS)
        mockMvc.perform(
                getUrlWithToken("/userData", goodToken))
                //.andDo(print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError)
    }

    @Test
    fun getAdminDataWithUserAuthenticatedWithRoleAdmin_ExpectSuccess() {
        val goodToken = testUtils.generateAccessTokenForMills(USER_WITH_ROLE_ADMIN, TOKEN_TIMEOUT_MS)
        mockMvc.perform(
                getUrlWithToken("/adminData", goodToken))
                //.andDo(print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
    }

    @Test
    fun getAdminDataWithUserAuthenticatedWithRoleAdmin_ExpectUnauthorized() {
        val goodToken = testUtils.generateAccessTokenForMills(USER_WITH_ROLE_USER, TOKEN_TIMEOUT_MS)
        mockMvc.perform(
                getUrlWithToken("/adminData", goodToken))
                //.andDo(print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError)
    }

    @Test
    fun getUserOrAdminDataWithUserAuthenticatedWithRoleUser_ExpectSuccess() {
        val goodTokenUser = testUtils.generateAccessTokenForMills(USER_WITH_ROLE_USER, TOKEN_TIMEOUT_MS)
        mockMvc.perform(
                getUrlWithToken("/userOrAdminData", goodTokenUser))
                //.andDo(print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
    }

    @Test
    fun getUserOrAdminDataWithUserAuthenticatedWithRoleAdmin_ExpectSuccess() {
        val goodTokenAdmin = testUtils.generateAccessTokenForMills(USER_WITH_ROLE_ADMIN, TOKEN_TIMEOUT_MS)
        mockMvc.perform(
                getUrlWithToken("/userOrAdminData", goodTokenAdmin))
                //.andDo(print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
    }
}

