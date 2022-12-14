package com.hanghae.blog.api.like.service;

import com.hanghae.blog.api.comment.entity.Comment;
import com.hanghae.blog.api.comment.repository.CommentRepository;
import com.hanghae.blog.api.like.entity.CommentLike;
import com.hanghae.blog.api.like.mapper.CommentLikeMapper;
import com.hanghae.blog.api.like.repository.CommentLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.hanghae.blog.api.common.exception.ExceptionMessage.NO_EXIST_COMMENT_EXCEPTION_MSG;

@Service
@RequiredArgsConstructor
public class CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;
    private final CommentLikeMapper commentLikeMapper;

    @Transactional
    public boolean updateLikeState(String username, Long commentId, boolean likeState) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException(NO_EXIST_COMMENT_EXCEPTION_MSG.getMsg()));
        long changeLikeCount;

        // 좋아요 취소 구현부
        if (likeState) {
            changeLikeCount = comment.getLikeCount() - 1; // 댓글 좋아요 카운트 -1
            comment.updateLikeCount(changeLikeCount);

            commentLikeRepository.deleteByCommentIdAndUsername(commentId, username);

            return false;
        }
        // 좋아요 구현부
        changeLikeCount = comment.getLikeCount() + 1;  // 댓글 좋아요 카운트 +1
        comment.updateLikeCount(changeLikeCount);

        CommentLike newCommentLike = commentLikeMapper.toCommentLike(username, commentId);
        commentLikeRepository.save(newCommentLike);

        return true;
    }
}
