package com.hanghae.blog.api.posting.controller;

import com.hanghae.blog.api.common.response.DataResponse;
import com.hanghae.blog.api.common.response.PageResponse;
import com.hanghae.blog.api.posting.dto.RequestCreatePosting;
import com.hanghae.blog.api.posting.dto.RequestPagePosting;
import com.hanghae.blog.api.posting.dto.ResponsePosting;
import com.hanghae.blog.api.posting.entity.Posting;
import com.hanghae.blog.api.posting.mapper.PostingMapper;
import com.hanghae.blog.api.posting.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Function;

import static com.hanghae.blog.api.common.response.ResponseMessage.CREATE_POSTING_SUCCESS_MSG;
import static com.hanghae.blog.api.common.response.ResponseMessage.READ_PAGING_POSTING_SUCCESS_MSG;
import static com.hanghae.blog.api.common.response.ResponseMessage.READ_POSTING_SUCCESS_MSG;

@RequiredArgsConstructor
@RequestMapping("/api/postings")
@RestController
public class PostingController {

    private final PostingService postingService;
    private final PostingMapper postingMapper;

    //게시글 등록
    @PostMapping
    public DataResponse<ResponsePosting> createPosting(@RequestBody RequestCreatePosting requestDto) {
        ResponsePosting response = postingService.create(requestDto);

        return new DataResponse<>(CREATE_POSTING_SUCCESS_MSG, response);
    }

    //전체 게시글 조회
    @GetMapping
    public DataResponse<List<ResponsePosting>> findAllPosting() {
        List<ResponsePosting> response = postingService.findAllPosting();

        return new DataResponse<>(READ_POSTING_SUCCESS_MSG, response);
    }

    @GetMapping("/list")
    public PageResponse<ResponsePosting, Posting> findPagePosting(RequestPagePosting requestDto) {
        Page<Posting> pageResult = postingService.findPagePosting(requestDto);
        Function<Posting, ResponsePosting> fn = p -> postingMapper.toResponse(p);

        return new PageResponse<>(READ_PAGING_POSTING_SUCCESS_MSG, pageResult, fn);
    }

    @GetMapping("/{id}")
    public DataResponse<ResponsePosting> findOnePosting(@PathVariable Long id){
        ResponsePosting response = postingService.findOnePosting(id);

        return new DataResponse<>(READ_POSTING_SUCCESS_MSG, response);
    }

}
