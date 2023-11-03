package com.samjay.BlogTask.dto.request;

import com.samjay.BlogTask.entity.Comment;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUserRequestDto {
    private Long id;
    private String username;
   // private List<Comment> comments = new ArrayList<>();
}
