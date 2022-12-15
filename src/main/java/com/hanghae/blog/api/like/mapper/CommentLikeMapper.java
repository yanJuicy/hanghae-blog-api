package com.hanghae.blog.api.like.mapper;

import com.hanghae.blog.api.comment.entity.Comment;
import com.hanghae.blog.api.like.entity.CommentLike;
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

