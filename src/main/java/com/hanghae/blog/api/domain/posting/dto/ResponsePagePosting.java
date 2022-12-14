package com.hanghae.blog.api.domain.posting.dto;

import com.hanghae.blog.api.domain.posting.entity.Posting;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@AllArgsConstructor
public class ResponsePagePosting {

    private Page<Posting> pageResult;
    private List<ResponsePosting> responsePostingDto;

}
