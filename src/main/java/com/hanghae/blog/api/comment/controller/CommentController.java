package com.hanghae.blog.api.comment.controller;

import com.hanghae.blog.api.comment.dto.RequestComment;
import com.hanghae.blog.api.comment.dto.ResponseComment;
import com.hanghae.blog.api.comment.service.CommentService;
import com.hanghae.blog.api.common.response.DataResponse;
import com.hanghae.blog.api.common.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.hanghae.blog.api.common.response.ResponseMessage.CREATE_COMMENT_SUCCESS_MSG;
import static com.hanghae.blog.api.common.response.ResponseMessage.DELETE_COMMENT_SUCCESS_MSG;
import static com.hanghae.blog.api.common.response.ResponseMessage.UPDATE_COMMENT_SUCCESS_MSG;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping()
    public DataResponse<ResponseComment> createComment(@PathVariable Long postId, @RequestBody RequestComment requestComment) {
        ResponseComment response = commentService.createComment(postId, requestComment);
        return new DataResponse<>(CREATE_COMMENT_SUCCESS_MSG, response);

    }

    // 댓글 및 대댓글 수정
    @PutMapping("/{commentId}")
    public DataResponse<ResponseComment> updateComment(@PathVariable Long commentId, @RequestBody RequestComment requestComment) {
        ResponseComment response = commentService.updateComment(commentId, requestComment);
        return new DataResponse<>(UPDATE_COMMENT_SUCCESS_MSG, response);
    }

    // 댓글 및 대댓글 삭제
    @DeleteMapping("/{commentId}")
    public Response deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return new Response(DELETE_COMMENT_SUCCESS_MSG);
    }

    @PostMapping("/{commentId}")
    public DataResponse<ResponseComment> createNestedComment(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody RequestComment requestComment) {
        ResponseComment response = commentService.createNestedComment(postId, commentId, requestComment);
        return new DataResponse<>(CREATE_COMMENT_SUCCESS_MSG, response);
    }

}
