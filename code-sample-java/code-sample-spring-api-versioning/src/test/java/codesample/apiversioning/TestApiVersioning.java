package codesample.apiversioning;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestApiVersioning {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testVersion16() throws Exception {
        mockMvc.perform(get("/")
            .header("version", "1.6"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello 1.6!")));
    }

    @Test
    public void testVersion17() throws Exception {
        mockMvc.perform(get("/")
                .header("version", "1.7"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello 1.7!")));
    }
}
