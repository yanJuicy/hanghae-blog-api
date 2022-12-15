package com.hanghae.blog.api.domain.posting.entity;

import com.hanghae.blog.api.domain.category_posting_map.entity.CategoryPostingMap;
import com.hanghae.blog.api.domain.comment.entity.Comment;
import com.hanghae.blog.api.common.entity.Timestamped;
import com.hanghae.blog.api.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Posting extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String password;


    @OneToMany(mappedBy = "posting")
    private List<CategoryPostingMap> categoryPostingMapList = new ArrayList<>();

    @OneToMany(mappedBy = "posting", fetch = FetchType.LAZY)
    private List<Comment> commentList = new ArrayList<>();

    public Posting(String title, User user, String contents, String password) {
        this.title = title;
        this.user = user;
        this.contents = contents;
        this.password = password;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }


}
