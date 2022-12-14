package com.hanghae.blog.api.like.mapper;

import com.hanghae.blog.api.like.entity.CommentLike;
import org.springframework.stereotype.Component;

@Component
public class CommentLikeMapper {

    public CommentLike toCommentLike(String username, Long commentId){
        return CommentLike.builder()
                .username(username)
                .commentId(commentId)
                .build();
    }
}

