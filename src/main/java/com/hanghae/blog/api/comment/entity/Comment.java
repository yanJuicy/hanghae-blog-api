package com.hanghae.blog.api.comment.entity;

import com.hanghae.blog.api.common.entity.Timestamped;
import com.hanghae.blog.api.like.entity.CommentLike;
import com.hanghae.blog.api.posting.entity.Posting;
import com.hanghae.blog.api.user.entity.User;
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


@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment extends Timestamped {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(nullable = false)
    private String content;

    @Column(columnDefinition = "bigint default 0")
    private Long likeCount;

    @ManyToOne(fetch = FetchType.LAZY)
    private Posting posting;

    @Column(nullable = false)
    private int commentDepth;

    private Long commentGroup;
//    @ManyToOne()
//    private Comment commentGroup;

    private Long commentRef;

    @OneToMany(mappedBy ="commentGroup", fetch = FetchType.LAZY)
    private List<Comment> nestedCommentList = new ArrayList<>();

    @OneToMany(mappedBy = "comment", fetch = FetchType.LAZY)
    private List<CommentLike> commentLikeList = new ArrayList<>();

    public void updateContent(String content) {
        this.content = content;
    }

    public void updateLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

}
