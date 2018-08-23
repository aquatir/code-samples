package codesample.kotlin.jwtexample.security

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvcBuilder

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class CorsTest {

    @Autowired
    lateinit var mockMvcBuilder: MockMvcBuilder

    @Test
    fun getAllTest() {

    }
}