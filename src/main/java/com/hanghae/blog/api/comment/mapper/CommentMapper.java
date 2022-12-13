package com.hanghae.blog.api.comment.mapper;

import com.hanghae.blog.api.comment.dto.RequestCreateCommentDto;
import com.hanghae.blog.api.comment.dto.ResponseCreateCommentDto;
import com.hanghae.blog.api.comment.entity.Comment;
import com.hanghae.blog.api.common.response.DataResponse;
import org.springframework.stereotype.Component;

import static com.hanghae.blog.api.common.response.ResponseMessage.CREATE_COMMENT_SUCCESS_MSG;

@Component
public class CommentMapper {

    public DataResponse<ResponseCreateCommentDto> toResponse(Comment comment) {
        ResponseCreateCommentDto response = ResponseCreateCommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .username(comment.getUsername())
                .createdAt(comment.getCreatedAt())
                .like(comment.getLikeCount())
                .build();

        return new DataResponse<>(CREATE_COMMENT_SUCCESS_MSG, response);
    }

    public Comment toDepthZeroComment(Long postId, RequestCreateCommentDto requestCreateCommentDto, Long likeCount){
        return Comment.builder()
                .content(requestCreateCommentDto.getContent())
                .username("test")
                .postId(postId)
                .likeCount(likeCount)
                .cDepth(0)
                .build();
    }



}
