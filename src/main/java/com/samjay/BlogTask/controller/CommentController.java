package com.samjay.BlogTask.controller;

import com.samjay.BlogTask.dto.request.CommentRequestDto;
import com.samjay.BlogTask.entity.Comment;
import com.samjay.BlogTask.mappers.Mapper;
import com.samjay.BlogTask.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    private CommentService commentService;
    private Mapper<Comment, CommentRequestDto> commentMapper;
    private Mapper<List<Comment>,List<CommentRequestDto>> commentListMapper;
    @Autowired
    public CommentController(CommentService commentService,
                             Mapper<Comment, CommentRequestDto> commentMapper,
                             Mapper<List<Comment>,List<CommentRequestDto>> commentListMapper) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
        this.commentListMapper = commentListMapper;
    }

    @PostMapping(path = "/addComment/{id}/{postId}")
    public ResponseEntity<CommentRequestDto> addComment(@RequestBody CommentRequestDto commentRequestDto,
                                                        @PathVariable("id") Long id,
                                                        @PathVariable("postId") Long postId
    ) {
        Comment comment = commentMapper.mapFrom(commentRequestDto);
        Comment savedComment = commentService.addComment(comment, id, postId);
        return new ResponseEntity<>(commentMapper.mapTo(savedComment), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteComment/{id}/{userId}")
    public ResponseEntity deleteAComment(@PathVariable("id") Long id,
                               @PathVariable("userId") Long userId) {
        commentService.deleteComment(id,userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/comments/{postId}")
    public ResponseEntity<List<CommentRequestDto>> getAllComments(@PathVariable("postId") Long postId){
      List<Comment> comments = commentService.getAllCommentsForAPost(postId);
      return  new ResponseEntity<>(commentListMapper.mapTo(comments),HttpStatus.OK);
    }


}
