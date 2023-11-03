package com.samjay.BlogTask.mappers.implementation;

import com.samjay.BlogTask.dto.response.PostResponseDto;
import com.samjay.BlogTask.entity.Post;
import com.samjay.BlogTask.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PostResponseMapperImplementation implements Mapper<Post, PostResponseDto> {
    private ModelMapper modelMapper;

    public PostResponseMapperImplementation(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PostResponseDto mapTo(Post post) {
        return modelMapper.map(post,PostResponseDto.class);
    }

    @Override
    public Post mapFrom(PostResponseDto postResponseDto) {
        return modelMapper.map(postResponseDto, Post.class);
    }
}
