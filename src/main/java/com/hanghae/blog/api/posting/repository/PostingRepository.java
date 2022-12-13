package com.hanghae.blog.api.posting.repository;


import com.hanghae.blog.api.posting.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostingRepository extends JpaRepository<Posting, Long> {


}
