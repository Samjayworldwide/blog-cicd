package com.samjay.BlogTask.service;

import com.samjay.BlogTask.entity.AppUser;
import com.samjay.BlogTask.entity.Comment;
import com.samjay.BlogTask.entity.Post;
import com.samjay.BlogTask.repository.AppUserRepository;
import com.samjay.BlogTask.repository.CommentRepository;
import com.samjay.BlogTask.repository.PostRepository;
import com.samjay.BlogTask.serviceImplementation.CommentServiceImplementation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private AppUserRepository appUserRepository;
    @Mock
    private PostRepository postRepository;
    @InjectMocks
    private CommentServiceImplementation commentService;


    @Test
    public void testIfACommentIsAddedToADataBase(){
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

        Comment comment = Comment
                .builder()
                .message("this is a lovely Post")
                .build();
        when(appUserRepository.findById(1L)).thenReturn(Optional.of(appUser));
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(commentRepository.save(Mockito.any(Comment.class))).thenReturn(comment);
        Comment savedComment = commentService.addComment(comment,1L,1L);

        Assertions.assertThat(savedComment).isNotNull();



    }

     @Test
    public void testIfCommentServiceGetsAllCommentsAssociatedToAPost(){
         Post post = Post
                 .builder()
                 .id(1L)
                 .content("beautiful Design")
                 .category("african")
                 .build();

         Comment comment = Comment
                 .builder()
                 .posts(post)
                 .message("this is a lovely Post")
                 .build();

         Comment comment1 = Comment
                 .builder()
                 .posts(post)
                 .message("this is a nice Post")
                 .build();


         when(postRepository.findById(1L)).thenReturn(Optional.of(post));
         when(commentRepository.findCommentsByPostsId(1L)).thenReturn(List.of(comment1,comment));
         List<Comment> commentList = commentService.getAllCommentsForAPost(1L);
         Assertions.assertThat(commentList).isNotNull();
         Assertions.assertThat(commentList.size()).isGreaterThan(1);
    }
}
