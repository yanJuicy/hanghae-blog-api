package com.hanghae.blog.api.posting.service;

import com.hanghae.blog.api.common.response.GenericResponseDto;
import com.hanghae.blog.api.posting.dto.RequestCreatePosting;
import com.hanghae.blog.api.posting.dto.ResponseCreatePosting;
import com.hanghae.blog.api.posting.entity.Posting;
import com.hanghae.blog.api.posting.mapper.PostingMapper;
import com.hanghae.blog.api.posting.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostingService {
    private final PostingRepository postingRepository;
	private final PostingMapper postingMapper;

    @Transactional
    public GenericResponseDto<ResponseCreatePosting> create(RequestCreatePosting requestDto) {
		Posting posting = postingMapper.toPosting(requestDto);

        Posting savedPosting = postingRepository.save(posting);

        return postingMapper.toResponse(savedPosting);
    }


}
