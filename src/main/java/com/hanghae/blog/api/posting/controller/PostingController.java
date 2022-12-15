package com.hanghae.blog.api.posting.controller;

import com.hanghae.blog.api.common.response.DataResponse;
import com.hanghae.blog.api.common.response.PageResponse;
import com.hanghae.blog.api.common.response.Response;
import com.hanghae.blog.api.posting.dto.RequestCreatePosting;
import com.hanghae.blog.api.posting.dto.RequestPagePosting;
import com.hanghae.blog.api.posting.dto.ResponsePosting;
import com.hanghae.blog.api.posting.entity.Posting;
import com.hanghae.blog.api.posting.mapper.PostingMapper;
import com.hanghae.blog.api.posting.service.PostingService;
import com.hanghae.blog.api.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static com.hanghae.blog.api.common.response.ResponseMessage.CREATE_POSTING_SUCCESS_MSG;
import static com.hanghae.blog.api.common.response.ResponseMessage.DELETE_POSTING_SUCCESS_MSG;
import static com.hanghae.blog.api.common.response.ResponseMessage.READ_PAGING_POSTING_SUCCESS_MSG;
import static com.hanghae.blog.api.common.response.ResponseMessage.READ_POSTING_SUCCESS_MSG;
import static com.hanghae.blog.api.common.response.ResponseMessage.UPDATE_POSTING_SUCCESS_MSG;

@RequiredArgsConstructor
@RequestMapping("/api/postings")
@RestController
public class PostingController {

    private final PostingService postingService;
    private final PostingMapper postingMapper;

    //게시글 등록
    @PostMapping
    public DataResponse<ResponsePosting> createPosting(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody RequestCreatePosting requestDto) {
        String username = userDetails.getUsername();
        ResponsePosting response = postingService.create(username, requestDto);

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
        List<String> categoryList = new ArrayList<>();
        Function<Posting, ResponsePosting> fn = p -> postingMapper.toResponse(p, categoryList,null);

        return new PageResponse<>(READ_PAGING_POSTING_SUCCESS_MSG, pageResult, fn);
    }
    //선택 게시글 조회
    @GetMapping("/{id}")
    public DataResponse<ResponsePosting> findOnePosting(@PathVariable Long id){
        ResponsePosting response = postingService.findOnePosting(id);

        return new DataResponse<>(READ_POSTING_SUCCESS_MSG, response);
    }
    //게시글 수정
    @PutMapping("/{id}")
    public DataResponse<ResponsePosting> updatePosting(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id, @RequestBody RequestCreatePosting requestCreatePosting) {
        String username = userDetails.getUsername();

        ResponsePosting response = postingService.updatePosting(id, requestCreatePosting);
        return new DataResponse<>(UPDATE_POSTING_SUCCESS_MSG, response);
    }
    //게시글 삭제
    @DeleteMapping("/{id}")
    public Response deletePosting(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id) {
        String username = userDetails.getUsername();
        postingService.deletePosting(id);
        return new Response (DELETE_POSTING_SUCCESS_MSG);
    }

}
