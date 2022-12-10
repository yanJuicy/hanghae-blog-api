package com.hanghae.blog.api.posting.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCreatePostingDto {

	private String title;
	private String writer;
	private String contents;
	private String password;
}
