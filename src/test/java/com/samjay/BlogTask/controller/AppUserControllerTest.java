package com.samjay.BlogTask.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samjay.BlogTask.entity.AppUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
public class AppUserControllerTest {
private MockMvc mockMvc;
private ObjectMapper objectMapper;
    @Autowired
    public AppUserControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testThatCreateAnAppUserReturnsHttp201Created() throws Exception {
        AppUser appUserA = TestDataUtil.createTestAppUserA();
        appUserA.setId(null);
       String appUserJson = objectMapper.writeValueAsString(appUserA);

        mockMvc.perform(MockMvcRequestBuilders.post("/createAppUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(appUserJson)

        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }
}
