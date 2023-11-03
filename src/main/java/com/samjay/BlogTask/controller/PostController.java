package com.samjay.BlogTask.controller;

import com.samjay.BlogTask.dto.request.PostRequestDto;
import com.samjay.BlogTask.dto.response.PostResponseDto;
import com.samjay.BlogTask.entity.Post;
import com.samjay.BlogTask.mappers.Mapper;
import com.samjay.BlogTask.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    private PostService postService;
    private Mapper<Post, PostRequestDto> postMapper;
    private Mapper<Post,PostResponseDto> postResponseMapper;
       @Autowired
    public PostController(PostService postService,
                          Mapper<Post, PostRequestDto> postMapper,
                          Mapper<Post,PostResponseDto> postResponseMapper) {
        this.postService = postService;
        this.postMapper = postMapper;
        this.postResponseMapper = postResponseMapper;
    }

    @PostMapping(path = "/createAPost/{id}")
    public ResponseEntity<PostRequestDto> createAPost(@RequestBody PostRequestDto postRequestDto,
                                                     @PathVariable("id") Long id){
           Post post = postMapper.mapFrom(postRequestDto);
          Post savedPost = postService.createPost(post,id);
          return new ResponseEntity<>(postMapper.mapTo(savedPost), HttpStatus.CREATED);
    }

    @PostMapping("/search/{content}")
    public ResponseEntity<PostResponseDto> findAPost(@PathVariable("content")String content){
         Post foundPost =  postService.findAPost(content);
         return new ResponseEntity<>(postResponseMapper.mapTo(foundPost),HttpStatus.FOUND);
    }

    @DeleteMapping("/deletePost/{userId}/{postId}")
    public ResponseEntity deleteAPost(@PathVariable("userId") Long userId, @PathVariable("postId") Long postId){
           postService.deleteAPost(userId,postId);
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
