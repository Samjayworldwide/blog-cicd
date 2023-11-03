package com.samjay.BlogTask.dto.request;

import com.samjay.BlogTask.entity.AppUser;
import com.samjay.BlogTask.entity.Post;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CommentRequestDto {
    private Long id;
    private String message;
//    private Set<AppUser> users = new HashSet<>();
//    private Post posts;
}
