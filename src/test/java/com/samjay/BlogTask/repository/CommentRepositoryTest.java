package com.samjay.BlogTask.repository;

import com.samjay.BlogTask.entity.AppUser;
import com.samjay.BlogTask.entity.Comment;
import com.samjay.BlogTask.entity.Post;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;

    @BeforeEach
    public void setUp(){
        AppUser appUser = AppUser
                .builder()
                .username("kingsley")
                .build();

        Post post = Post
                .builder()
                .content("beautiful Design")
                .category("african")
                .build();
        Comment comment = Comment
                .builder()
                .appUser(appUser)
                .posts(post)
                .message("this is a lovely post")
                .build();

             commentRepository.save(comment);

    }

    @Test
    public void testIfCommentRepositorySavesAComment(){
        AppUser appUser = AppUser
                .builder()
                .username("kingsley")
                .build();

        Post post = Post
                .builder()
                .content("beautiful Design")
                .category("african")
                .build();
        Comment comment = Comment
                .builder()
                .appUser(appUser)
                .posts(post)
                .message("this is a lovely post")
                .build();

         Comment savedComment = commentRepository.save(comment);

        Assertions.assertThat(savedComment).isNotNull();
        Assertions.assertThat(savedComment.getId()).isGreaterThan(0);
    }


    @Test
    public void testThatCommentRepositoryFindsCommentsByPostId(){
        List<Comment> commentList = commentRepository.findCommentsByPostsId(1L);
        Assertions.assertThat(commentList).isNotNull();
        Assertions.assertThat(commentList.size()).isGreaterThan(0);
    }
    @Test
    public void testThatCommentRepositoryDeletesACommentByID(){
        assertAll(()->commentRepository.deleteById(1L));
    }

}
