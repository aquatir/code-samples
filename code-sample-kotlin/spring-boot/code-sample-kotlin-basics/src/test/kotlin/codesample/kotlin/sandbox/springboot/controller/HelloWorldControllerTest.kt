package codesample.kotlin.sandbox.springboot.controller

import codesample.kotlin.sandbox.springboot.db.CustomerRepository
import codesample.kotlin.sandbox.springboot.entity.Customer
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
    @Autowired
    lateinit var customerRepository: CustomerRepository

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
        mockMvc.perform(
            post("/customer")
                .param("name", destroyTable)
        )
            .andDo(print())
            .andExpect(status().isOk)
    }

    @Test
    fun testSave() {
        mockMvc.perform(get("/save"))
            .andDo(print())
            .andExpect(status().isOk)
    }

    /** Hibernate is actually updating entity even if it is immutable,
     * so please don't write code like this in production*/
    @Test
    fun save() {
        val customerOne = Customer("Foo", "Foo")
        val customerTwo = Customer("Bar", "Bar")

        val set = setOf(customerOne, customerTwo)

        with(customerRepository) {
            val custOneAfterSave = save(customerOne)
            val custTwoAfterSave = save(customerTwo)

            println("is custOneBefore in set? : ${set.contains(customerOne)}")
            println("is custTwoBefore in set? : ${set.contains(customerTwo)}")
            println("is custOneAfter in set? : ${set.contains(custOneAfterSave)}")
            println("is custTwoAfter in set? : ${set.contains(custTwoAfterSave)}")

            println("is beforeOne equal afterOne? ${customerOne == custOneAfterSave}")
            println("is beforeTwo equal afterTwo? ${customerTwo == custTwoAfterSave}")
        }

        println(set)
    }
}