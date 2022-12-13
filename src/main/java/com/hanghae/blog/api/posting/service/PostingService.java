package com.hanghae.blog.api.posting.service;


import com.hanghae.blog.api.common.response.DataResponse;
import com.hanghae.blog.api.posting.dto.RequestCreatePosting;
import com.hanghae.blog.api.posting.dto.RequestPageFindPosting;
import com.hanghae.blog.api.posting.dto.ResponseCreatePosting;
import com.hanghae.blog.api.posting.dto.ResponsePageFindPosting;
import com.hanghae.blog.api.posting.dto.ResponsePosting;
import com.hanghae.blog.api.posting.entity.Posting;
import com.hanghae.blog.api.posting.mapper.PostingMapper;
import com.hanghae.blog.api.posting.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class PostingService {
    private final PostingRepository postingRepository;
	private final PostingMapper postingMapper;


    @Transactional

    public DataResponse<ResponseCreatePosting> create(RequestCreatePosting requestDto) {

		Posting posting = postingMapper.toPosting(requestDto);

        Posting savedPosting = postingRepository.save(posting);

        return postingMapper.toResponse(savedPosting);
    }

    @Transactional
    public List<ResponseCreatePosting> findAllPosting(){
        List<Posting> Posting = postingRepository.findAll();

        List<ResponseCreatePosting> result = new ArrayList<>();

        for(Posting p : Posting){
            result.add(new ResponseCreatePosting(p));
        }
        return result;
    }


    public ResponsePageFindPosting<ResponsePosting, Posting> findPagePosting(RequestPageFindPosting requestDto) {
        Pageable pageable = requestDto.getPageable(Sort.by("id"));
        Page<Posting> result = postingRepository.findAll(pageable);
        Function<Posting, ResponsePosting> fn = p -> postingMapper.toResponsePosting(p);

        return new ResponsePageFindPosting<>(result, fn);
    }

}
