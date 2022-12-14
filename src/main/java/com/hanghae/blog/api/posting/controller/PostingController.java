package com.hanghae.blog.api.posting.controller;

import com.hanghae.blog.api.common.response.DataResponse;
import com.hanghae.blog.api.posting.dto.RequestCreatePosting;
import com.hanghae.blog.api.posting.dto.RequestPagePosting;
import com.hanghae.blog.api.posting.dto.ResponseCreatePosting;
import com.hanghae.blog.api.common.response.PageResponse;
import com.hanghae.blog.api.posting.dto.ResponsePosting;
import com.hanghae.blog.api.posting.entity.Posting;
import com.hanghae.blog.api.posting.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/list")
    public PageResponse<ResponsePosting, Posting> findPagePosting(RequestPagePosting requestDto) {
        return postingService.findPagePosting(requestDto);
    }

    @GetMapping("/{id}")
    public RequestCreatePosting findOnePosting(@PathVariable Long id){
        return postingService.findOnePosting(id);
    }

}
