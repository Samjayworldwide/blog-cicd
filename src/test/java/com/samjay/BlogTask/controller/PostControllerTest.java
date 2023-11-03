package com.samjay.BlogTask.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samjay.BlogTask.entity.Post;
import com.samjay.BlogTask.service.PostService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class PostControllerTest {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    @MockBean
    private PostService postService;
      @Autowired
    public PostControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testIfPostControllerCreatesANewPostAndReturn200Created() throws Exception {
        Post post = Post
                .builder()
                .content("the beauty of street wears")
                .category("street category")
                .build();
        post.setId(1L);

        String postJson = objectMapper.writeValueAsString(post);
        mockMvc.perform(MockMvcRequestBuilders.post("/createAPost/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(postJson)

        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testIfPostControllerCanDeleteAPostAndReturnHttpStatus204Ok() throws Exception {
        Post post = Post
                .builder()
                .content("the beauty of street wears")
                .category("street category")
                .build();
        post.setId(1L);
        postService.createPost(post,1L);
        mockMvc.perform(MockMvcRequestBuilders.delete("/deletePost/1/1")
                .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(MockMvcResultMatchers.status().isNoContent());

    }
}
