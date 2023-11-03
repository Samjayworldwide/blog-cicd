package com.samjay.BlogTask.service;

import com.samjay.BlogTask.entity.Post;

import java.util.List;


public interface PostService {
    Post createPost(Post post,Long id);
    Post findAPost(String postContent);
    void deleteAPost(Long userId, Long postId);
}
