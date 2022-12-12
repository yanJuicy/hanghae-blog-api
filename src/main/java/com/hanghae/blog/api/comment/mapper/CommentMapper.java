package com.hanghae.blog.api.comment.mapper;

import com.hanghae.blog.api.comment.dto.RequestCreateCommentDto;
import com.hanghae.blog.api.comment.dto.ResponseCreateCommentDto;
import com.hanghae.blog.api.comment.entity.Comment;
import com.hanghae.blog.api.common.response.GenericResponseDto;
import com.hanghae.blog.api.common.response.ResponseMessage;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public GenericResponseDto<ResponseCreateCommentDto> toResponse(Comment comment) {
        ResponseCreateCommentDto response = ResponseCreateCommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .username(comment.getUsername())
                .createdAt(comment.getCreatedAt())
                .like(comment.getLikeCount())
                .build();

        return new GenericResponseDto<>(ResponseMessage.CREATE_COMMENT_SUCCESS_MSG, response);
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
