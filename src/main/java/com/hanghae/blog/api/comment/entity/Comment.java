package com.hanghae.blog.api.comment.entity;

import com.hanghae.blog.api.common.entity.Timestamped;
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
import javax.persistence.OneToMany;
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

    @Column(nullable = false, name = "username")
    private String username; // user로 변경 될 곳
//    @Column(nullable = false, name = "username")
//    private User user;

    @Column(nullable = false)
    private String content;

    @Column(columnDefinition = "bigint default 0")
    private Long likeCount;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private int cDepth;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Comment comment;

    private Long commentId;

    @OneToMany(mappedBy ="commentId", fetch = FetchType.LAZY)
    private List<Comment> nestedCommentList;


    public void updateContent(String content) {
        this.content = content;
    }




}
