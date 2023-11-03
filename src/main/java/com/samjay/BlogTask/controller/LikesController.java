package com.samjay.BlogTask.controller;

import com.samjay.BlogTask.dto.request.LikesRequestDto;
import com.samjay.BlogTask.entity.Likes;
import com.samjay.BlogTask.mappers.Mapper;
import com.samjay.BlogTask.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikesController {
    private LikeService likeService;
    private Mapper<Likes, LikesRequestDto> likesMapper;
     @Autowired
    public LikesController(LikeService likeService,Mapper<Likes, LikesRequestDto> likesMapper) {
        this.likeService = likeService;
        this.likesMapper = likesMapper;
    }

    @PostMapping("/addLike/{userId}/{postId}")
   public LikesRequestDto addLikes(@RequestBody LikesRequestDto likesRequestDto,
                                   @PathVariable("userId") Long userId,
                                   @PathVariable("postId") Long postId){
         Likes likes = likesMapper.mapFrom(likesRequestDto);
         Likes savedLike = likeService.addLike(likes,userId,postId);
         return likesMapper.mapTo(savedLike);
   }
}
