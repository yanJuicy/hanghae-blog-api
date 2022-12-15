package com.hanghae.blog.api.domain.like.service;

import com.hanghae.blog.api.domain.comment.entity.Comment;
import com.hanghae.blog.api.domain.comment.repository.CommentRepository;
import com.hanghae.blog.api.domain.like.entity.CommentLike;
import com.hanghae.blog.api.domain.like.mapper.CommentLikeMapper;
import com.hanghae.blog.api.domain.like.repository.CommentLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.hanghae.blog.api.common.exception.ExceptionMessage.DUPLICATION_COMMENT_LIKE_EXCEPTION_MSG;
import static com.hanghae.blog.api.common.exception.ExceptionMessage.NO_EXIST_COMMENT_EXCEPTION_MSG;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;
    private final CommentLikeMapper commentLikeMapper;

    @Transactional
    public boolean updateLikeState(String username, Long commentId, boolean likeState) {
        // 좋아요 할 댓글
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException(NO_EXIST_COMMENT_EXCEPTION_MSG.getMsg()));
        // 좋아요한 상태인지 체크
        Optional<CommentLike> commentLike = commentLikeRepository.findByCommentIdAndUsername(commentId, username);

        long changeLikeCount;

        // 좋아요 취소 구현부
        if (likeState && commentLike.isPresent()) { // 좋아요 상태이며, 좋아요 테이블에도 데이터가 있는 경우

            changeLikeCount = comment.getLikeCount() - 1; // 댓글 좋아요 카운트 -1
            comment.updateLikeCount(changeLikeCount);

            commentLikeRepository.deleteByCommentIdAndUsername(commentId, username);

            return false;
        }

        if(likeState){ // 좋아요 상태이나, 좋아요 테이블에 데이터가 없는 경우
            return false;
        }

        // 좋아요 구현부
        if(commentLike.isPresent()){ // 좋아요하지 않은 상태이나, 좋아요 테이블에 데이터가 있는 경우
            throw new IllegalArgumentException(DUPLICATION_COMMENT_LIKE_EXCEPTION_MSG.getMsg());
        }

        // 좋아요하지않은 상태이나, 좋아요 테이블에 데이터가 없는 경우
        changeLikeCount = comment.getLikeCount() + 1;  // 댓글 좋아요 카운트 +1
        comment.updateLikeCount(changeLikeCount);

        CommentLike newCommentLike = commentLikeMapper.toCommentLike(username, comment);
        commentLikeRepository.save(newCommentLike);

        return true;
    }
}
