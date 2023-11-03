package com.samjay.BlogTask.repository;

import com.samjay.BlogTask.dto.request.CommentRequestDto;
import com.samjay.BlogTask.entity.Comment;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findCommentsByPostsId(Long postId);
    @Query("SELECT c, u FROM Comment c JOIN c.appUser u WHERE c.posts.id = :postId")
    List<Object[]> findCommentsAndAppUserByPostsId(Long postId);
}
