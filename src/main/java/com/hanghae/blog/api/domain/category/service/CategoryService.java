package com.hanghae.blog.api.domain.category.service;

import com.hanghae.blog.api.domain.category.entity.Category;
import com.hanghae.blog.api.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public List<Category> saveCategories(List<String> categoryNameList) {
        List<Category> categoryList = new ArrayList<>();

        for (String categoryName : categoryNameList) {
            Optional<Category> savedCategory = categoryRepository.findByName(categoryName);
            if (savedCategory.isPresent()) {
                categoryList.add(savedCategory.get());
                continue;
            }

            Category category = new Category(categoryName);
            categoryRepository.save(category);
            categoryList.add(category);
        }

        return categoryList;
    }

}
