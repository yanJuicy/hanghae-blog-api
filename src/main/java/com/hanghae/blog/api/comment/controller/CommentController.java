package com.hanghae.blog.api.comment.controller;

import com.hanghae.blog.api.comment.dto.RequestComment;
import com.hanghae.blog.api.comment.dto.ResponseComment;
import com.hanghae.blog.api.comment.service.CommentService;
import com.hanghae.blog.api.common.response.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.hanghae.blog.api.common.response.ResponseMessage.CREATE_COMMENT_SUCCESS_MSG;
import static com.hanghae.blog.api.common.response.ResponseMessage.UPDATE_COMMENT_SUCCESS_MSG;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{postId}/comments")
    public DataResponse<ResponseComment> createComment(@PathVariable Long postId, @RequestBody RequestComment requestComment){
        ResponseComment response = commentService.createComment(postId, requestComment);

        return new DataResponse<>(CREATE_COMMENT_SUCCESS_MSG, response);

    }

    @PutMapping("/comments/{commentId}")
    public DataResponse<ResponseComment> updateComment(@PathVariable Long commentId, @RequestBody RequestComment requestComment){
        ResponseComment response = commentService.updateComment(commentId, requestComment);
        return new DataResponse<>(UPDATE_COMMENT_SUCCESS_MSG, response);
    }

}
