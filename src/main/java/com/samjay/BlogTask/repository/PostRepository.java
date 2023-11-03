package com.samjay.BlogTask.repository;

import com.samjay.BlogTask.entity.Comment;
import com.samjay.BlogTask.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    Post findPostByContent(String content);

//    @Query("SELECT p FROM Post p LEFT JOIN FETCH p.comments c WHERE p.category = :category")
//    Post findPostWithComments(@Param("category") String category);
//    @Query("SELECT p FROM Post p LEFT JOIN FETCH p.likes l WHERE p.category = :category")
//    Post findPostWithLikes(@Param("category") String category);
}
