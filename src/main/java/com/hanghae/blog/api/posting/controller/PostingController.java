package com.hanghae.blog.api.posting.controller;


import com.hanghae.blog.api.common.response.DataResponse;
import com.hanghae.blog.api.posting.dto.RequestCreatePosting;
import com.hanghae.blog.api.posting.dto.ResponseCreatePosting;

import com.hanghae.blog.api.posting.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/api/postings")
@RestController
public class PostingController {

    private final PostingService postingService;
    //게시글 등록
    @PostMapping

    public DataResponse<ResponseCreatePosting> createPosting(@RequestBody RequestCreatePosting requestDto) {

        return postingService.create(requestDto);
    }

    //전체 게시글 조회
    @GetMapping
    public List<ResponseCreatePosting>  findAllPosting() {
        return postingService.findAllPosting();
    }


}
