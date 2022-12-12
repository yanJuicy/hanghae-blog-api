package com.hanghae.blog.api.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestCreateCommentDto {
    private String content;
}
