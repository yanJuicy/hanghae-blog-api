package com.hanghae.blog.api.comment.controller;

import com.hanghae.blog.api.comment.dto.RequestComment;
import com.hanghae.blog.api.comment.dto.ResponseComment;
import com.hanghae.blog.api.comment.service.CommentService;
import com.hanghae.blog.api.common.response.DataResponse;
import com.hanghae.blog.api.common.response.Response;
import com.hanghae.blog.api.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public DataResponse<ResponseComment> createComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postId, @RequestBody RequestComment requestComment) {
        String username = userDetails.getUsername();
        ResponseComment response = commentService.createComment(username,postId, requestComment);
        return new DataResponse<>(CREATE_COMMENT_SUCCESS_MSG, response);

    }

    // 댓글 및 대댓글 수정
    @PutMapping("/{commentId}")
    public DataResponse<ResponseComment> updateComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long commentId, @RequestBody RequestComment requestComment) {
        String username = userDetails.getUsername();
        ResponseComment response = commentService.updateComment(username, commentId, requestComment);
        return new DataResponse<>(UPDATE_COMMENT_SUCCESS_MSG, response);
    }

    // 댓글 및 대댓글 삭제
    @DeleteMapping("/{commentId}")
    public Response deleteComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long commentId) {
        String username = userDetails.getUsername();
        commentService.deleteComment(username, commentId);
        return new Response(DELETE_COMMENT_SUCCESS_MSG);
    }

    @PostMapping("/{commentId}")
    public DataResponse<ResponseComment> createNestedComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postId, @PathVariable Long commentId, @RequestBody RequestComment requestComment) {
        String username = userDetails.getUsername();

        ResponseComment response = commentService.createNestedComment(username, postId, commentId, requestComment);
        return new DataResponse<>(CREATE_COMMENT_SUCCESS_MSG, response);
    }

}
