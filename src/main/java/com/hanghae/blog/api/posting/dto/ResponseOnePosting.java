package com.hanghae.blog.api.posting.dto;

import com.hanghae.blog.api.comment.dto.ResponseComment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ResponseOnePosting {

    private Long id;
    private String title;
    private String writer;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
    private List<List<ResponseComment>> comments;
}
