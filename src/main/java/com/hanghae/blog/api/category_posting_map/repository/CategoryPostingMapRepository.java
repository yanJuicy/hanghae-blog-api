package com.hanghae.blog.api.category_posting_map.repository;

import com.hanghae.blog.api.category.entity.Category;
import com.hanghae.blog.api.category_posting_map.entity.CategoryPostingMap;
import com.hanghae.blog.api.posting.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryPostingMapRepository extends JpaRepository<CategoryPostingMap, Long> {

    List<CategoryPostingMap> findAllByPosting(Posting posting);
    List<CategoryPostingMap> findAllByCategory(Category category);

}