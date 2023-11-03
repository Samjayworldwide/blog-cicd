package com.samjay.BlogTask.mappers.implementation;

import com.samjay.BlogTask.dto.request.CommentRequestDto;
import com.samjay.BlogTask.entity.Comment;
import com.samjay.BlogTask.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CommentListMapperImplementation implements Mapper<List<Comment>,List<CommentRequestDto>> {
    private ModelMapper modelMapper;
     @Autowired
    public CommentListMapperImplementation(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CommentRequestDto> mapTo(List<Comment> comments) {
        return comments
                .stream()
                .map(comment-> modelMapper.map(comment,CommentRequestDto.class))
                .toList();
    }

    @Override
    public List<Comment> mapFrom(List<CommentRequestDto> commentRequestDto) {
        return commentRequestDto
                .stream()
                .map(commentRequestDto1 -> modelMapper.map(commentRequestDto1, Comment.class))
                .toList();
    }
}
