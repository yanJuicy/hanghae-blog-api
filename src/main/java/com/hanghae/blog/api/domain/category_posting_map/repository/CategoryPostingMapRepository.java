package com.hanghae.blog.api.domain.category_posting_map.repository;

import com.hanghae.blog.api.domain.category.entity.Category;
import com.hanghae.blog.api.domain.category_posting_map.entity.CategoryPostingMap;
import com.hanghae.blog.api.domain.posting.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryPostingMapRepository extends JpaRepository<CategoryPostingMap, Long> {

    List<CategoryPostingMap> findAllByPosting(Posting posting);
    List<CategoryPostingMap> findAllByCategory(Category category);

    void deleteAllByPosting(Posting posting);
}
