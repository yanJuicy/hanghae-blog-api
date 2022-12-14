package com.hanghae.blog.api.like.repository;

import com.hanghae.blog.api.like.entity.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

     void deleteByCommentIdAndUsername(Long CommentId, String username);

}
