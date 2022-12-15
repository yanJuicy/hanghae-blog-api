package com.hanghae.blog.api.comment.mapper;

import com.hanghae.blog.api.comment.dto.RequestComment;
import com.hanghae.blog.api.comment.dto.ResponseComment;
import com.hanghae.blog.api.comment.entity.Comment;
import com.hanghae.blog.api.posting.entity.Posting;
import com.hanghae.blog.api.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
                .commentRef(comment.getCommentRef())
                .build();
    }

    public List<ResponseComment> toResponseCommentList(Comment comment){
        List<ResponseComment> result = new ArrayList<>();
        result.add(toResponse(comment));

        List<Comment> nestedCommentList = comment.getNestedCommentList();
        if (nestedCommentList.size() != 0) {
            for (Comment c : nestedCommentList) {
                System.out.println(c.getId());
                result.addAll(toResponseCommentList(c));
            }
        }
        return result;
    }

    public Comment toDepthZeroComment(User user, Posting posting, RequestComment requestCreateCommentDto){
        return Comment.builder()
                .content(requestCreateCommentDto.getContent())
                .user(user)
                .posting(posting)
                .likeCount(0L)
                .commentDepth(0)
                .commentGroup(0L)
                .build();
    }

    public Comment toNestedComment(User user, Posting posting, RequestComment requestComment, Long commentId, int commentDepth, Long commentRef){
        return Comment.builder()
                .content(requestComment.getContent())
                .user(user)
                .posting(posting)
                .likeCount(0L)
                .commentDepth(commentDepth)
                .commentGroup(commentId)
                .commentRef(commentRef)
                .build();
    }


}
