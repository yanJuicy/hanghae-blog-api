package com.hanghae.blog.api.comment.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ResponseCreateCommentDto {

    private Long id;
    private String content;
    private String username;
    private LocalDateTime createdAt;
    private Long like;

}
