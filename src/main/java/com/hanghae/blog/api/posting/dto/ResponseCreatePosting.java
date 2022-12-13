package com.hanghae.blog.api.posting.dto;


import com.hanghae.blog.api.posting.entity.Posting;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCreatePosting {

	private Long id;
	private String title;
	private String writer;
	private String contents;

	public ResponseCreatePosting(Posting posting){
		this.id=posting.getId();
		this.title= posting.getTitle();
		this.writer=posting.getWriter();
		this.contents= posting.getContents();
	}


}
