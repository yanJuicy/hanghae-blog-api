package com.hanghae.blog.api.domain.posting.repository;


import com.hanghae.blog.api.domain.posting.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostingRepository extends JpaRepository<Posting, Long> {

}
