package com.samjay.BlogTask.dto.request;

import com.samjay.BlogTask.entity.Comment;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRequestDto {
    private Long id;
    private String content;
    private String category;
   // private List<Comment> comments = new ArrayList<>();
}
