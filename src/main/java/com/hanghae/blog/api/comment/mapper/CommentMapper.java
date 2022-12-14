package com.hanghae.blog.api.comment.mapper;

import com.hanghae.blog.api.comment.dto.RequestComment;
import com.hanghae.blog.api.comment.dto.ResponseComment;
import com.hanghae.blog.api.comment.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public ResponseComment toResponse(Comment comment) {

        return ResponseComment.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .username(comment.getUsername())
                .createdAt(comment.getCreatedAt())
                .like(comment.getLikeCount())
                .cDepth(comment.getCDepth())
                .commentId(comment.getCommentId())
                .build();
    }

    public Comment toDepthZeroComment(Long postId, RequestComment requestCreateCommentDto, Long likeCount){
        return Comment.builder()
                .content(requestCreateCommentDto.getContent())
                .username("test")
                .postId(postId)
                .likeCount(likeCount)
                .cDepth(0)
                .build();
    }

    public Comment toNestedComment(Long postId, RequestComment requestComment, Long likeCount, Long commentId, int cDepth){
        return Comment.builder()
                .content(requestComment.getContent())
                .username("test")
                .postId(postId)
                .likeCount(likeCount)
                .cDepth(cDepth)
                .commentId(commentId)
                .build();
    }


}
