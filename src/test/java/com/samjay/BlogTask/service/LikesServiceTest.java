package com.samjay.BlogTask.service;

import com.samjay.BlogTask.entity.AppUser;
import com.samjay.BlogTask.entity.Likes;
import com.samjay.BlogTask.entity.Post;
import com.samjay.BlogTask.repository.AppUserRepository;
import com.samjay.BlogTask.repository.LikeRepository;
import com.samjay.BlogTask.repository.PostRepository;
import com.samjay.BlogTask.serviceImplementation.LikeServiceImplementation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LikesServiceTest {
    @Mock
    private AppUserRepository appUserRepository;
    @Mock
    private PostRepository postRepository;
    @Mock
    private LikeRepository likeRepository;
    @InjectMocks
    private LikeServiceImplementation likeService;


    @Test
    public void testIfLikesCanBeAddedToAPost(){

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
        Likes likes = Likes
                .builder()
                .likes(1L)
                .build();

        when(appUserRepository.findById(1L)).thenReturn(Optional.of(appUser));
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(likeRepository.save(Mockito.any(Likes.class))).thenReturn(likes);
        Likes savedLike = likeService.addLike(likes,1L,1L);
        Assertions.assertThat(savedLike).isNotNull();
        Assertions.assertThat(savedLike.getLikes()).isGreaterThan(0);
    }
}
