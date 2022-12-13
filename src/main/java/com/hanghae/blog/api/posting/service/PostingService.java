package com.hanghae.blog.api.posting.service;


import com.fasterxml.jackson.databind.DatabindException;
import com.hanghae.blog.api.common.response.DataResponse;
import com.hanghae.blog.api.posting.dto.RequestCreatePosting;
import com.hanghae.blog.api.posting.dto.ResponseCreatePosting;


import com.hanghae.blog.api.posting.entity.Posting;
import com.hanghae.blog.api.posting.mapper.PostingMapper;
import com.hanghae.blog.api.posting.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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




}
