package com.hanghae.blog.api.posting.mapper;

import com.hanghae.blog.api.comment.dto.ResponseComment;
import com.hanghae.blog.api.posting.dto.RequestCreatePosting;
import com.hanghae.blog.api.posting.dto.ResponseOnePosting;
import com.hanghae.blog.api.posting.dto.ResponsePosting;
import com.hanghae.blog.api.posting.entity.Posting;
import com.hanghae.blog.api.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public ResponseOnePosting toResponseOnePosting(Posting posting, List<List<ResponseComment>> comments){
        return ResponseOnePosting.builder()
                .id(posting.getId())
                .title(posting.getTitle())
                .writer(posting.getUser().getUsername())
                .contents(posting.getContents())
                .createdAt(posting.getCreatedAt())
                .lastModifiedAt(posting.getLastModifiedAt())
                .comments(comments)
                .build();
    }

    public Posting toPosting(User user, RequestCreatePosting requestDto) {
        return new Posting(requestDto.getTitle(),
                user,
                requestDto.getContents(),
                requestDto.getPassword());
    }
}
