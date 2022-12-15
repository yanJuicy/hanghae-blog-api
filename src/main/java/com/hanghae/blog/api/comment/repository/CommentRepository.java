package com.hanghae.blog.api.comment.repository;

import com.hanghae.blog.api.comment.entity.Comment;
import com.hanghae.blog.api.posting.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // cDepth 최대값 가져오는 쿼리
    @Query(value = "select max(commentDepth) from Comment where commentGroup= :commentId")
    Optional<Integer> findWithComment(@Param(value = "commentId") Long commentId);
    
    void deleteByCommentGroup(Long commentGroup);
    

    List<Comment> findAllByPostingOrderByCreatedAtDesc(Posting p);

    void deleteAllByPosting(Posting posting);
}
