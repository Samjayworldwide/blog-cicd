package com.samjay.BlogTask.mappers.implementation;

import com.samjay.BlogTask.dto.request.PostRequestDto;
import com.samjay.BlogTask.dto.response.PostResponseDto;
import com.samjay.BlogTask.entity.Post;
import com.samjay.BlogTask.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PostMapperImplementation implements Mapper<Post, PostRequestDto> {

    private ModelMapper modelMapper;

    public PostMapperImplementation(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PostRequestDto mapTo(Post post) {
        return modelMapper.map(post, PostRequestDto.class);
    }

    @Override
    public Post mapFrom(PostRequestDto postRequestDto) {
        return modelMapper.map(postRequestDto, Post.class);
    }
}
