package com.hanghae.blog.api.like.controller;

import com.hanghae.blog.api.common.response.DataResponse;
import com.hanghae.blog.api.like.dto.comments.LikeState;
import com.hanghae.blog.api.like.service.CommentLikeService;
import com.hanghae.blog.api.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.hanghae.blog.api.common.response.ResponseMessage.CREATE_COMMENT_LIKE_SUCCESS_MSG;
import static com.hanghae.blog.api.common.response.ResponseMessage.DELETE_COMMENT_LIKE_SUCCESS_MSG;

@RestController
@RequestMapping("/api/posts/comments")
@RequiredArgsConstructor
public class CommentLikeController {

    private final CommentLikeService commentLikeService;

    @PostMapping("/{commentId}/like")
    public DataResponse<LikeState> updateLikeState(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long commentId, @RequestBody LikeState likeState) {
        String username = userDetails.getUsername();

        boolean result = commentLikeService.updateLikeState(username, commentId, likeState.isLikeState());

        if (result) {
            return new DataResponse<>(CREATE_COMMENT_LIKE_SUCCESS_MSG, new LikeState(true));
        }
        return new DataResponse<>(DELETE_COMMENT_LIKE_SUCCESS_MSG, new LikeState(false));
    }
}
