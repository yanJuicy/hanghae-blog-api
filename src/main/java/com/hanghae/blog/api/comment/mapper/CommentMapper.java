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
                .commentDepth(comment.getCommentDepth())
                .commentGroup(comment.getCommentGroup())
                .build();
    }

    public Comment toDepthZeroComment(Long postId, RequestComment requestCreateCommentDto){
        return Comment.builder()
                .content(requestCreateCommentDto.getContent())
                .username("test")
                .postId(postId)
                .likeCount(0L)
                .commentDepth(0)
                .build();
    }

    public Comment toNestedComment(Long postId, RequestComment requestComment, Long commentId, int commentDepth){
        return Comment.builder()
                .content(requestComment.getContent())
                .username("test")
                .postId(postId)
                .likeCount(0L)
                .commentDepth(commentDepth)
                .commentGroup(commentId)
                .build();
    }


}
