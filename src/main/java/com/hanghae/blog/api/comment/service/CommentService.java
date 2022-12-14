package com.hanghae.blog.api.comment.service;

import com.hanghae.blog.api.comment.dto.RequestComment;
import com.hanghae.blog.api.comment.dto.ResponseComment;
import com.hanghae.blog.api.comment.entity.Comment;
import com.hanghae.blog.api.comment.mapper.CommentMapper;
import com.hanghae.blog.api.comment.repository.CommentRepository;
import com.hanghae.blog.api.posting.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.hanghae.blog.api.common.exception.ExceptionMessage.NO_EXIST_COMMENT_EXCEPTION_MSG;

@Service
@RequiredArgsConstructor
public class CommentService {

    private  final CommentMapper commentMapper;
    private final PostingRepository postingRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public ResponseComment createComment(Long postId, RequestComment requestComment){
        Comment newComment = commentMapper.toDepthZeroComment(postId, requestComment, 0L);

        postingRepository.findById(postId)
                .orElseThrow(() -> new NullPointerException("게시글이 존재하지 않습니다."));

        commentRepository.save(newComment);

        return commentMapper.toResponse(newComment);

    }

   @Transactional
    public ResponseComment updateComment(Long commentId, RequestComment requestComment){

        Comment commentFind = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException(NO_EXIST_COMMENT_EXCEPTION_MSG.getMsg()));

        String updateComment = requestComment.getContent();
        commentFind.updateContent(updateComment);

        return commentMapper.toResponse(commentFind);
    }

    @Transactional
    public String deleteComment(Long commentId){

        commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException(NO_EXIST_COMMENT_EXCEPTION_MSG.getMsg()));

        commentRepository.deleteById(commentId);

        return "success";
    }
}
