package com.hanghae.blog.api.domain.category.entity;

import com.hanghae.blog.api.domain.category_posting_map.entity.CategoryPostingMap;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Category {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<CategoryPostingMap> categoryPostingMapList = new ArrayList<>();

    protected Category() {}

    public Category(String name) {
        this.name = name;
    }

}
