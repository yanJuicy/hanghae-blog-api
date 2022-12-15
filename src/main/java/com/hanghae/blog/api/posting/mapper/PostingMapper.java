package com.hanghae.blog.api.posting.mapper;

import com.hanghae.blog.api.comment.dto.ResponseComment;
import com.hanghae.blog.api.posting.dto.RequestCreatePosting;
import com.hanghae.blog.api.posting.dto.ResponsePosting;
import com.hanghae.blog.api.posting.entity.Posting;
import com.hanghae.blog.api.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostingMapper {

    public ResponsePosting toResponse(Posting posting, List<String> categoryList, List<ResponseComment> commentList) {
        return ResponsePosting.builder()
                .id(posting.getId())
                .title(posting.getTitle())
                .writer(posting.getUser().getUsername())
                .contents(posting.getContents())
                .createdAt(posting.getCreatedAt())
                .lastModifiedAt(posting.getLastModifiedAt())
                .categories(categoryList)
                .commentList(commentList)
                .build();
    }


    public Posting toPosting(User user, RequestCreatePosting requestDto) {
        return new Posting(
                requestDto.getTitle(),
                user,
                requestDto.getContents());
    }
}
