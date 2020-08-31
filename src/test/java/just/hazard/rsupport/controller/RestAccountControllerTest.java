package just.hazard.rsupport.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import just.hazard.rsupport.domain.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RestAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void restLogin() throws Exception {
        // given
        Account account = new Account();
        account.setEmail("admin");
        account.setPassword("1234");

        // when
        mockMvc.perform(post("/rest/login",account)
                // then
                .contentType(MediaType.APPLICATION_JSON)
                .content(serialize(account)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("success"));
    }

    private String serialize(Account account) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(account);
    }

}