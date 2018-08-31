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

    private val tokenTimeoutMs = 10000L

    /**
     * Test GET on /data endpoint without token does not work
     */
    @Test
    fun getDataTestNoToken() {
        mockMvc.perform(get("/data"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isUnauthorized)
    }

    /**
     * Test GET on /data endpoint WITH correct token DOES work
     */
    @Test
    fun getDataTestWithToken() {
        val goodToken = testUtils.generateAccessTokenForSeconds(1, tokenTimeoutMs)
        mockMvc.perform(
                getUrlWithToken("/data", goodToken))
                //.andDo(print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
    }

    /**
     * Test GET on /userData works for user with role USER (id 1)
     */
    @Test
    fun getUserDataWithUserTest() {
        val goodToken = testUtils.generateAccessTokenForSeconds(1, tokenTimeoutMs)
        mockMvc.perform(
                getUrlWithToken("/userData", goodToken))
                //.andDo(print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
    }

    /**
     * Test GET on /userData DOES NOT work for user with role ADMIN (id 3)
     */
    @Test
    fun getUserDataWithAdminTest() {
        val goodToken = testUtils.generateAccessTokenForSeconds(3, tokenTimeoutMs)
        mockMvc.perform(
                getUrlWithToken("/userData", goodToken))
                //.andDo(print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError)
    }

    /**
     * Test GET on /adminData works for user with role ADMIN (id 2)
     */
    @Test
    fun getAdminDataWithAdminTest() {
        val goodToken = testUtils.generateAccessTokenForSeconds(2, tokenTimeoutMs)
        mockMvc.perform(
                getUrlWithToken("/adminData", goodToken))
                //.andDo(print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
    }

    /**
     * Test GET on /adminData DOES NOT work for user with role USER (id 1)
     */
    @Test
    fun getAdminDataWithUserTest() {
        val goodToken = testUtils.generateAccessTokenForSeconds(1, tokenTimeoutMs)
        mockMvc.perform(
                getUrlWithToken("/adminData", goodToken))
                //.andDo(print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError)
    }

    /**
     * Test GET on /userOrAdminData works both for USER and ADMIN
     */
    @Test
    fun getUserAdminDataWithBothUserAndAdminTest() {
        val goodTokenAdmin = testUtils.generateAccessTokenForSeconds(2, tokenTimeoutMs)
        mockMvc.perform(
                getUrlWithToken("/userOrAdminData", goodTokenAdmin))
                //.andDo(print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)

        val goodTokenUser = testUtils.generateAccessTokenForSeconds(1, tokenTimeoutMs)
        mockMvc.perform(
                getUrlWithToken("/userOrAdminData", goodTokenUser))
                //.andDo(print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
    }
}

