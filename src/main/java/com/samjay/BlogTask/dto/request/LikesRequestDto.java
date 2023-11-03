package com.samjay.BlogTask.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikesRequestDto {
    private Long id;
    private Long likes;
}
