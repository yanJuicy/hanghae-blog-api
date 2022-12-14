package com.hanghae.blog.api.posting.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCreatePosting {

	private String title;
	private String writer;
	private String contents;
	private String password;
	
	public RequestCreatePosting(Posting posting) {
		this.id=posting.getId();
		this.title= posting.getTitle();
		this.writer=posting.getWriter();
		this.contents= posting.getContents();
	}
}
