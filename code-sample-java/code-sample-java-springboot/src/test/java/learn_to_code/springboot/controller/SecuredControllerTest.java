package learn_to_code.springboot.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SecuredControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @Test /* Not authenticated user will be redirected */
    public void testSecuredIndexNotAuthenticated() throws Exception {
        mockMvc.perform(get("/secure/"))
                .andExpect(status().is3xxRedirection());
    }

    @Test /* Authenticated */
    @WithMockUser(username = "user")
    public void testSecuredIndexAuthenticated() throws Exception {
        mockMvc.perform(
                get("/secure/"))
                .andExpect(status().isOk());
    }
}
