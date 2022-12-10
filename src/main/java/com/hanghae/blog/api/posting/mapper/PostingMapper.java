package com.hanghae.blog.api.posting.mapper;


import com.hanghae.blog.api.common.response.GenericResponseDto;
import com.hanghae.blog.api.posting.dto.RequestCreatePostingDto;
import com.hanghae.blog.api.posting.dto.ResponseCreatePostingDto;
import com.hanghae.blog.api.posting.entity.Posting;
import org.springframework.stereotype.Component;

import static com.hanghae.blog.api.common.response.ResponseMessage.CREATE_POSTING_SUCCESS_MSG;

@Component
public class PostingMapper {

    public GenericResponseDto<ResponseCreatePostingDto> toResponse(Posting posting) {
        ResponseCreatePostingDto response = ResponseCreatePostingDto.builder()
                .id(posting.getId())
                .title(posting.getTitle())
                .writer(posting.getWriter())
                .contents(posting.getContents())
                .build();

        return new GenericResponseDto<>(CREATE_POSTING_SUCCESS_MSG, response);
    }

    public Posting toPosting(RequestCreatePostingDto requestDto) {
        return new Posting(requestDto.getTitle(),
                requestDto.getWriter(),
                requestDto.getContents(),
                requestDto.getPassword());
    }
}
