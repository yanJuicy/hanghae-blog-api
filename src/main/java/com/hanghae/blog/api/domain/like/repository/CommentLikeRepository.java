package com.hanghae.blog.api.domain.like.repository;

import com.hanghae.blog.api.domain.comment.entity.Comment;
import com.hanghae.blog.api.domain.like.entity.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

     Optional<CommentLike> findByCommentIdAndUsername(Long CommentId, String username);

     void deleteByCommentIdAndUsername(Long CommentId, String username);

     void deleteCommentLikeByComment(Comment c);
}
