package just.hazard.rsupport.controller;

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
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static io.florianlopes.spring.test.web.servlet.request.MockMvcRequestBuilderUtils.postForm;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockHttpSession session = new MockHttpSession();

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        Account account = new Account();
        account.setId(1L);
        account.setEmail("admin");
        account.setPassword("1234");

        session.setAttribute("user",account);
    }

    @Test
    public void list() throws Exception {

        mockMvc.perform(get("/board/list")
                .contentType(MediaType.TEXT_HTML))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("/board/list"))
                .andExpect(model().attributeExists("list"));
    }

    @Test
    public void getWrite() throws Exception {
        mockMvc.perform(get("/board/write")
                .contentType(MediaType.TEXT_HTML))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("/board/write"));
    }

    @Test
    public void postWrite() throws Exception {

        Account user = (Account) session.getAttribute("user");

        Notice notice = new Notice();
        notice.setTitle("제목");
        notice.setContent("내용");
        notice.setUser(user);

        mockMvc.perform(postForm("/board/write", notice)
                .contentType(MediaType.TEXT_HTML)
                .session(session))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/board/list"));


        Long count = boardRepository.count();
        notice = boardRepository.getOne(count+1);
        Assertions.assertEquals("제목",notice.getTitle());
        Assertions.assertEquals("내용",notice.getContent());
        Assertions.assertEquals("admin",notice.getUser().getEmail());
    }

    @Test
    public void read() throws Exception {

        Optional<Notice> notice = boardRepository.findById(1L);

        mockMvc.perform(get("/board/read")
                .contentType(MediaType.TEXT_HTML)
                .param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("board/read"))
                .andExpect(model().attribute("result",notice.get()));
    }

    @Test
    public void update() throws Exception {

        Account user = (Account) session.getAttribute("user");

        Notice notice = new Notice();
        notice.setId(1L);
        notice.setTitle("제목수정");
        notice.setContent("내용수정");
        notice.setUser(user);

        mockMvc.perform(postForm("/board/write", notice)
                .contentType(MediaType.TEXT_HTML)
                .session(session))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/board/list"));

        notice = boardRepository.getOne(1L);
        Assertions.assertEquals("제목수정",notice.getTitle());
        Assertions.assertEquals("내용수정",notice.getContent());
    }

    @Test
    public void delete() throws Exception {

        Account user = (Account) session.getAttribute("user");

        mockMvc.perform(get("/board/delete")
                .contentType(MediaType.TEXT_HTML)
                .param("id", "1")
                .session(session))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/board/list"));

        Optional<Notice> notice = boardRepository.findById(1L);
        Assertions.assertEquals(Optional.empty(), notice);

    }
}
