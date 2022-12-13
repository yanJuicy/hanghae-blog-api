package com.hanghae.blog.api.posting.mapper;


import com.hanghae.blog.api.common.response.DataResponse;
import com.hanghae.blog.api.posting.dto.RequestCreatePosting;
import com.hanghae.blog.api.posting.dto.ResponseCreatePosting;

import com.hanghae.blog.api.posting.entity.Posting;
import org.springframework.stereotype.Component;

import static com.hanghae.blog.api.common.response.ResponseMessage.CREATE_POSTING_SUCCESS_MSG;

@Component
public class PostingMapper {


    public DataResponse<ResponseCreatePosting> toResponse(Posting posting) {
        ResponseCreatePosting response = ResponseCreatePosting.builder()

                .id(posting.getId())
                .title(posting.getTitle())
                .writer(posting.getWriter())
                .contents(posting.getContents())
                .build();

        return new DataResponse<>(CREATE_POSTING_SUCCESS_MSG, response);
    }

    public Posting toPosting(RequestCreatePosting requestDto) {
        return new Posting(requestDto.getTitle(),
                requestDto.getWriter(),
                requestDto.getContents(),
                requestDto.getPassword());
    }
}
