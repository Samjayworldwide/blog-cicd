package com.samjay.BlogTask.mappers.implementation;

import com.samjay.BlogTask.dto.request.LikesRequestDto;
import com.samjay.BlogTask.entity.Likes;
import com.samjay.BlogTask.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LikeMapperImplementation implements Mapper<Likes, LikesRequestDto> {
    private ModelMapper modelMapper;

    public LikeMapperImplementation(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public LikesRequestDto mapTo(Likes likes) {
        return modelMapper.map(likes, LikesRequestDto.class);
    }

    @Override
    public Likes mapFrom(LikesRequestDto likesRequestDto) {
        return modelMapper.map(likesRequestDto, Likes.class);
    }
}
