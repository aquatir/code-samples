package codesample.kotlin.sandbox.springboot.controller

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class HelloWorldControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun getAllTest() {
        mockMvc.perform(get("/all"))
                .andDo(print())
                .andExpect(status().isOk)
    }

    @Test
    fun getUserByName() {
        val name = "Jack"
        val destroyTable = "' or '1'='1"

        /* Doesn't work! */
        mockMvc.perform(post("/customer")
                .param("name", destroyTable))
                .andDo(print())
                .andExpect(status().isOk)
    }
}