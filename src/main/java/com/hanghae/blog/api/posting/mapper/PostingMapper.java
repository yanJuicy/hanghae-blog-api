package com.hanghae.blog.api.posting.mapper;

import com.hanghae.blog.api.posting.dto.RequestCreatePosting;
import com.hanghae.blog.api.posting.dto.ResponsePosting;
import com.hanghae.blog.api.posting.entity.Posting;
import org.springframework.stereotype.Component;

@Component
public class PostingMapper {

    public ResponsePosting toResponse(Posting posting) {
        return ResponsePosting.builder()
                .id(posting.getId())
                .title(posting.getTitle())
                .writer(posting.getWriter())
                .contents(posting.getContents())
                .createdAt(posting.getCreatedAt())
                .lastModifiedAt(posting.getLastModifiedAt())
                .build();
    }


    public Posting toPosting(RequestCreatePosting requestDto) {
        return new Posting(requestDto.getTitle(),
                requestDto.getWriter(),
                requestDto.getContents(),
                requestDto.getPassword());
    }
}
