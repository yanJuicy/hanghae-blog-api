package com.hanghae.blog.api.posting.mapper;

import com.hanghae.blog.api.comment.dto.ResponseComment;
import com.hanghae.blog.api.comment.mapper.CommentMapper;
import com.hanghae.blog.api.posting.dto.RequestCreatePosting;
import com.hanghae.blog.api.posting.dto.ResponsePosting;
import com.hanghae.blog.api.posting.entity.Posting;
import com.hanghae.blog.api.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class PostingMapper {

    private final CommentMapper commentMapper;

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

    public ResponsePosting toResponse(Posting posting) {
        // 포스팅의 카테고리들
        List<String> categoryList = posting.getCategoryPostingMapList().stream()
                .map(e -> e.getCategory())
                .map(c -> c.getName())
                .collect(Collectors.toList());

        // 포스팅의 댓글들
        List<ResponseComment> commentList = posting.getCommentList().stream()
                .map(e -> commentMapper.toResponse(e))
                .collect(Collectors.toList());

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
                requestDto.getContents(),
                requestDto.getPassword());
    }
}
