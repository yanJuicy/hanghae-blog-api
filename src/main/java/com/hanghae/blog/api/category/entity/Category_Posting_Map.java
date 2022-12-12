package com.hanghae.blog.api.category.entity;

import com.hanghae.blog.api.posting.entity.Posting;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Category_Posting_Map {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Posting posting;

    protected Category_Posting_Map() {}

}
