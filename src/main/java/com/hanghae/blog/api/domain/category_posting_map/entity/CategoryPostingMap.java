package com.hanghae.blog.api.domain.category_posting_map.entity;

import com.hanghae.blog.api.domain.category.entity.Category;
import com.hanghae.blog.api.domain.posting.entity.Posting;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Entity
public class CategoryPostingMap {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Posting posting;

    protected CategoryPostingMap() {}

    public CategoryPostingMap(Category category, Posting posting) {
        this.category = category;
        this.posting = posting;
    }

}
