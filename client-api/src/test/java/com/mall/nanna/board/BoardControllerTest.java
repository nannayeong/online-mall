package com.mall.nanna.board;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.nanna.board.controller.BoardController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BoardControllerTest {

    private final MockMvc mockMvc;

    private static String ROOT = "/board";

    private BoardControllerTest(@Autowired MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void addBoard() throws Exception {

        Map<String, Object> content = new HashMap<String, Object>() {{
            put("title", "제목입니다.");
            put("text", "본문입니다.");
            put("password", "1234");
        }};

        mockMvc.perform(post(ROOT + "")
                        .content(new ObjectMapper().writeValueAsString(content))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)

                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andReturn();
    }

    @Test
    public void updateBoard() throws Exception {

        Map<String, Object> content = new HashMap<String, Object>() {{
            put("title", "제목입니다.수정");
            put("text", "본문입니다.수정");
            put("password", "1234");
        }};

        mockMvc.perform(put(ROOT + "/1")
                        .content(new ObjectMapper().writeValueAsString(content))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)

                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andReturn();
    }

    @Test
    public void deleteBoard() throws Exception {

        Map<String, Object> content = new HashMap<String, Object>() {{
            put("password", "1234");
        }};

        mockMvc.perform(delete(ROOT + "/1")
                        .content(new ObjectMapper().writeValueAsString(content))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)

                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andReturn();
    }

    @Test
    public void findBoard() throws Exception {

        mockMvc.perform(get(ROOT + "/2")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)

                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andReturn();
    }

    @Test
    public void findBoardList() throws Exception {

        mockMvc.perform(get(ROOT + "")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("limit", "10")
                        .param("page", "1")
                        .param("searchColumn", "title")
                        .param("searchWord", "제목")
                        .param("directionColumn", "dateCreate")
                        .param("direction", "desc")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andReturn();
    }
}
