package com.hanghae.blog.api.comment.repository;

import com.hanghae.blog.api.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
