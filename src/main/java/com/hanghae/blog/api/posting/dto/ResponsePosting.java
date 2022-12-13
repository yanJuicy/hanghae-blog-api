package com.hanghae.blog.api.posting.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ResponsePosting {

    private Long id;
    private String title;
    private String writer;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;

}
