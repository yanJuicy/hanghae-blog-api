package com.hanghae.blog.api.posting.controller;



import com.hanghae.blog.api.common.response.DataResponse;
import com.hanghae.blog.api.posting.dto.RequestCreatePostingDto;
import com.hanghae.blog.api.posting.dto.ResponseCreatePostingDto;

import com.hanghae.blog.api.posting.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RequestMapping("/api/postings")
@RestController
public class PostingController {

    private final PostingService postingService;

    @PostMapping

    public DataResponse<ResponseCreatePostingDto> createPosting(@RequestBody RequestCreatePostingDto requestDto) {

        return postingService.create(requestDto);
    }


}
