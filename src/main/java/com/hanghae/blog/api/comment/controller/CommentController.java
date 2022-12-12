package com.hanghae.blog.api.comment.controller;

import com.hanghae.blog.api.comment.dto.RequestCreateCommentDto;
import com.hanghae.blog.api.comment.dto.ResponseCreateCommentDto;
import com.hanghae.blog.api.comment.service.CommentService;
import com.hanghae.blog.api.common.response.GenericResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{postId}/comments")
    public GenericResponseDto<ResponseCreateCommentDto> createComment(@PathVariable Long postId, @RequestBody RequestCreateCommentDto requestCreateCommentDto){
        System.out.println();
        return commentService.createComment(postId, requestCreateCommentDto);

    }


}
