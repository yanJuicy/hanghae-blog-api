package com.hanghae.blog.api.posting.mapper;

import com.hanghae.blog.api.posting.dto.RequestCreatePosting;
import com.hanghae.blog.api.posting.dto.ResponsePosting;
import com.hanghae.blog.api.posting.entity.Posting;
import com.hanghae.blog.api.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class PostingMapper {

    public ResponsePosting toResponse(Posting posting) {
        return ResponsePosting.builder()
                .id(posting.getId())
                .title(posting.getTitle())
                .writer(posting.getUser().getUsername())
                .contents(posting.getContents())
                .createdAt(posting.getCreatedAt())
                .lastModifiedAt(posting.getLastModifiedAt())
                .build();
    }


    public Posting toPosting(User user, RequestCreatePosting requestDto) {
        return new Posting(requestDto.getTitle(),
                user,
                requestDto.getContents(),
                requestDto.getPassword());
    }
}
