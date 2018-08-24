package codesample.kotlin.jwtexample.controller

import codesample.kotlin.jwtexample.security.service.JwtTokenService
import codesample.kotlin.jwtexample.util.TestUtils
import codesample.kotlin.jwtexample.util.TestUtils.Companion.getUrlWithToken
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
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

    @Test
    fun getDataTestNoToken() {
        mockMvc.perform(get("/data"))
                //.andDo(print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError)
    }

    @Test
    fun getDataTestWithToken() {
        val goodToken = testUtils.generateTokenForSeconds(1, 10)
        mockMvc.perform(
                getUrlWithToken("/data", goodToken))
                //.andDo(print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
    }
}

