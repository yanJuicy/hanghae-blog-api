package com.hanghae.blog.api.posting.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Posting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String password;

	public Posting(String title, String writer, String contents, String password) {
		this.title = title;
		this.writer = writer;
		this.contents = contents;
		this.password = password;
	}


}
