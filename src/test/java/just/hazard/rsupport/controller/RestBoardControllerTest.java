package just.hazard.rsupport.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import just.hazard.rsupport.domain.Account;
import just.hazard.rsupport.domain.Notice;
import just.hazard.rsupport.repository.BoardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class RestBoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BoardRepository boardRepository;


    private Account account;

    private Notice notice;

    @BeforeEach
    public void setup() {

        account = new Account();
        account.setId(1L);
        account.setEmail("admin");
        account.setPassword("1234");

        notice = new Notice();
        notice.setTitle("제목");
        notice.setContent("내용");
        notice.setUser(account);
    }

    @Test
    public void list() throws Exception {
         mockMvc.perform(get("/rest/board")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("content[0].id").exists())
                .andExpect(jsonPath("content[0].id").value("1"))
                .andExpect(jsonPath("content[0].title").exists())
                .andExpect(jsonPath("content[0].title").value("제목0"))
                .andExpect(jsonPath("content[0].content").exists())
                .andExpect(jsonPath("content[0].content").value("내용"))
                .andExpect(jsonPath("content[0].startDate").exists())
                .andExpect(jsonPath("content[0].updateDate").exists());

    }

    @Test
    public void write() throws Exception {
        mockMvc.perform(post("/rest/board")
                .contentType(MediaType.APPLICATION_JSON)
                .content(serialize()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("제목"))
                .andExpect(jsonPath("$.content").value("내용"))
                .andExpect(jsonPath("$.user.email").value("admin"));
    }

    @Test
    public void update() throws Exception {

        notice.setTitle("제목수정");
        notice.setContent("내용수정");

        mockMvc.perform(put("/rest/board/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(serialize()))
                .andExpect(status().isNoContent());

        Notice notice = boardRepository.getOne(1L);
        Assertions.assertEquals("제목수정",notice.getTitle());
        Assertions.assertEquals("내용수정",notice.getContent());
        Assertions.assertEquals(1L,notice.getUser().getId());
    }

    @Test
    public void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/rest/board/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        Assertions.assertThrows(JpaObjectRetrievalFailureException.class, () -> {
            boardRepository.getOne(1L);
        });
    }

    private String serialize() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(notice);
    }
}