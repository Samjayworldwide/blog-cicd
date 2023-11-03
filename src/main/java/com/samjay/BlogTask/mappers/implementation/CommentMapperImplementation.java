package com.samjay.BlogTask.mappers.implementation;

import com.samjay.BlogTask.dto.request.CommentRequestDto;
import com.samjay.BlogTask.entity.Comment;
import com.samjay.BlogTask.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CommentMapperImplementation implements Mapper<Comment, CommentRequestDto> {
    private ModelMapper modelMapper;

    public CommentMapperImplementation(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentRequestDto mapTo(Comment comment) {
        return modelMapper.map(comment, CommentRequestDto.class);
    }

    @Override
    public Comment mapFrom(CommentRequestDto commentRequestDto) {
        return modelMapper.map(commentRequestDto, Comment.class);
    }
}
