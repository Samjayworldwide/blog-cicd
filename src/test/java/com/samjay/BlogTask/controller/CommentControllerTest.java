package com.samjay.BlogTask.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.samjay.BlogTask.dto.request.AppUserRequestDto;
import com.samjay.BlogTask.dto.request.PostRequestDto;
import com.samjay.BlogTask.entity.AppUser;
import com.samjay.BlogTask.entity.Comment;
import com.samjay.BlogTask.entity.Post;
import com.samjay.BlogTask.service.AppUserService;
import com.samjay.BlogTask.service.CommentService;
import com.samjay.BlogTask.service.PostService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
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
public class CommentControllerTest {
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;
    private PostService postService;
    private AppUserService appUserService;
    private ObjectMapper objectMapper;

      @Autowired
    public CommentControllerTest(MockMvc mockMvc, PostService postService,AppUserService appUserService) {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
        this.postService = postService;
        this.appUserService = appUserService;
    }


    @Test
    public void checkIfCommentControllerGetsAllCommentsAssociatedWithAPostAndReturnsHttp200Ok() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.get("/comments/1")
              .contentType(MediaType.APPLICATION_JSON)

      ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testIfCommentControllerCreatesANewComment() throws Exception {
            Comment comment = Comment
                    .builder()
                    .message("lovely Post")
                    .build();
            comment.setId(null);
        String postJson = objectMapper.writeValueAsString(comment);
        mockMvc.perform(MockMvcRequestBuilders.post("/addComment/1/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(postJson)

        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

@Test
public void testIfCommentControllerDeleteMappingReturnsHttpStatus204ForNonExistingComment() throws Exception {
    Comment comment = Comment
            .builder()
            .message("lovely Post")
            .build();
    comment.setId(1L);
    commentService.addComment(comment,1L,1L);
    mockMvc.perform(MockMvcRequestBuilders.delete("/deleteComment/1/1")
            .contentType(MediaType.APPLICATION_JSON)

    ).andExpect(MockMvcResultMatchers.status().isNoContent());
}

















//    @Test
//    public void testThatListOfCommentReturnsComments() throws Exception {
//        AppUser appUser = AppUser
//                .builder()
//                .id(1L)
//                .comments(null)
//                .likes(null)
//                .posts(null)
//                .username("samuel")
//                .build();
//       AppUser savedAppUser = appUserService.createAnAppUser(appUser);
//        ModelMapper modelMapper = new ModelMapper();
//       AppUserRequestDto appUserRequestDto = modelMapper.map(savedAppUser, AppUserRequestDto.class);
//
//
//        Post post = Post
//                .builder()
//                .id(1L)
//                .content(" african style")
//                .appUser(appUser)
//                .likes(null)
//                .comments(null)
//                .category("african")
//                .build();
//       Post savedPost = postService.createPost(post, appUserRequestDto.getId());
//        PostRequestDto postRequestDto = modelMapper.map(savedPost,PostRequestDto.class);
//
//
//        Comment comment = Comment
//                .builder()
//                .message("lovely Post")
//                .appUser(appUser)
//                .posts(post)
//                .build();
//        commentService.addComment(comment, postRequestDto.getId(), appUserRequestDto.getId());
//
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/comments/"+postRequestDto.getId())
//                .contentType(MediaType.APPLICATION_JSON)
//        ).andExpect(
//                MockMvcResultMatchers.jsonPath("$[0].message").value("lovely Post")
//        ).andExpect(MockMvcResultMatchers.status().isOk());
//    }
}
