package com.hanghae.blog.api.category_posting_map.service;

import com.hanghae.blog.api.category.entity.Category;
import com.hanghae.blog.api.category_posting_map.entity.CategoryPostingMap;
import com.hanghae.blog.api.category_posting_map.repository.CategoryPostingMapRepository;
import com.hanghae.blog.api.posting.entity.Posting;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryPostingMapService {

    private final CategoryPostingMapRepository categoryPostingMapRepository;

    @Transactional
    public void saveCategoryPostingMap(List<Category> categoryList, Posting posting) {
        for (Category category : categoryList) {
            categoryPostingMapRepository.save(new CategoryPostingMap(category, posting));
        }
    }

    @Transactional(readOnly = true)
    public List<Category> findCategories(Posting posting) {
        List<CategoryPostingMap> foundCategoryPostingMapList = categoryPostingMapRepository.findAllByPosting(posting);
        List<Category> categoryList = foundCategoryPostingMapList.stream()
                .map(e -> e.getCategory())
                .collect(Collectors.toList());

        return categoryList;
    }

    public void deleteById(Posting posting) {
    }
}
