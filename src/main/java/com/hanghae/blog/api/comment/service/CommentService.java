package com.hanghae.blog.api.comment.service;

import com.hanghae.blog.api.comment.dto.RequestCreateCommentDto;
import com.hanghae.blog.api.comment.dto.ResponseCreateCommentDto;
import com.hanghae.blog.api.comment.entity.Comment;
import com.hanghae.blog.api.comment.mapper.CommentMapper;
import com.hanghae.blog.api.comment.repository.CommentRepository;
import com.hanghae.blog.api.common.response.GenericResponseDto;
import com.hanghae.blog.api.posting.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private  final CommentMapper commentMapper;
    private final PostingRepository postingRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public GenericResponseDto<ResponseCreateCommentDto> createComment(Long postId, RequestCreateCommentDto requestCreateCommentDto){
        Comment newComment = commentMapper.toDepthZeroComment(postId, requestCreateCommentDto, 0L);
        postingRepository.findById(postId).orElseThrow(() -> new NullPointerException("게시글이 존재하지 않습니다."));
        commentRepository.save(newComment);

        return commentMapper.toResponse(newComment);

    }

}
