package com.hanghae.blog.api.domain.comment.mapper;

import com.hanghae.blog.api.domain.comment.dto.RequestComment;
import com.hanghae.blog.api.domain.comment.dto.ResponseComment;
import com.hanghae.blog.api.domain.comment.entity.Comment;
import com.hanghae.blog.api.domain.posting.entity.Posting;
import com.hanghae.blog.api.domain.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public ResponseComment toResponse(Comment comment) {
        return ResponseComment.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .username(comment.getUser().getUsername())
                .createdAt(comment.getCreatedAt())
                .like(comment.getLikeCount())
                .commentDepth(comment.getCommentDepth())
                .commentGroup(comment.getCommentGroup())
                .build();
    }

    public Comment toDepthZeroComment(User user, Posting posting, RequestComment requestCreateCommentDto){
        return Comment.builder()
                .content(requestCreateCommentDto.getContent())
                .user(user)
                .posting(posting)
                .likeCount(0L)
                .commentDepth(0)
                .build();
    }

    public Comment toNestedComment(User user, Posting posting, RequestComment requestComment, Long commentId, int commentDepth){
        return Comment.builder()
                .content(requestComment.getContent())
                .user(user)
                .posting(posting)
                .likeCount(0L)
                .commentDepth(commentDepth)
                .commentGroup(commentId)
                .build();
    }


}
