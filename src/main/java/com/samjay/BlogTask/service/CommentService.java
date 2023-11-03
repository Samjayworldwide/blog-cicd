package com.samjay.BlogTask.service;

import com.samjay.BlogTask.dto.request.CommentRequestDto;
import com.samjay.BlogTask.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment addComment(Comment comment,Long id,Long postId);
    void deleteComment(Long id,Long userId);
    List<Comment> getAllCommentsForAPost(Long postId);
}
