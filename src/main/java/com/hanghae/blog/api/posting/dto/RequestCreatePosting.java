package com.hanghae.blog.api.posting.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCreatePosting {

	private String title;
	private String writer;
	private String contents;
	private String password;
	private List<String> categories = new ArrayList<>();

}
