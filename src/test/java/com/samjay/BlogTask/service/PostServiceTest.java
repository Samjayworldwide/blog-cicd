package com.samjay.BlogTask.service;

import com.samjay.BlogTask.entity.AppUser;
import com.samjay.BlogTask.entity.Post;
import com.samjay.BlogTask.repository.AppUserRepository;
import com.samjay.BlogTask.repository.PostRepository;
import com.samjay.BlogTask.serviceImplementation.PostServiceImplementation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {
    @Mock
    private PostRepository postRepository;

    @Mock
    private AppUserRepository appUserRepository;

    @InjectMocks
    private PostServiceImplementation postService;


    @Test
    public void testIfThePostServiceSavesAPostInTheDataBase(){

        AppUser appUser = AppUser
                .builder()
                .id(1L)
                .username("kingsley")
                .build();

      appUserRepository.save(appUser);

        Post post = Post
                .builder()
                .content("beautiful Design")
                .category("african")
                .build();
        when(appUserRepository.findById(1L)).thenReturn(Optional.of(appUser));
        when(postRepository.save(Mockito.any(Post.class))).thenReturn(post);
        Post savedPost = postService.createPost(post,1L);
        Assertions.assertThat(savedPost).isNotNull();
    }

    @Test
    public void testIfThePostServiceFindsAPostByItsContent(){
        Post post = Post
                .builder()
                .content("beautiful Design")
                .category("african")
                .build();
        when(postRepository.findPostByContent("beautiful Design")).thenReturn(post);
        Post foundPost = postService.findAPost("beautiful Design");
        Assertions.assertThat(foundPost).isNotNull();
        Assertions.assertThat(foundPost.getCategory()).isEqualTo(post.getCategory());
    }

     @Test
    public void testIfThePostServiceDeletesAPostByAGivenId(){
        AppUser appUser = AppUser
                .builder()
                .id(1L)
                .username("kingsley")
                .build();


        Post post = Post
                .builder()
                .id(1L)
                .content("beautiful Design")
                .category("african")
                .build();

        when(appUserRepository.findById(1L)).thenReturn(Optional.of(appUser));
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        assertAll(()-> postService.deleteAPost(1L,1L));


    }
}
