package com.hanghae.blog.api.domain.like.mapper;

import com.hanghae.blog.api.domain.comment.entity.Comment;
import com.hanghae.blog.api.domain.like.entity.CommentLike;
import org.springframework.stereotype.Component;

@Component
public class CommentLikeMapper {

    public CommentLike toCommentLike(String username, Comment comment){
        return CommentLike.builder()
                .username(username)
                .comment(comment)
                .build();
    }
}

