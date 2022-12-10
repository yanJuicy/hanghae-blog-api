package com.hanghae.blog.api.posting.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCreatePostingDto {

	private Long id;
	private String title;
	private String writer;
	private String contents;
}
