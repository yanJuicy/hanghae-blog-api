package com.hanghae.blog.api.posting.service;

import com.hanghae.blog.api.posting.dto.RequestCreatePosting;
import com.hanghae.blog.api.posting.dto.RequestPagePosting;
import com.hanghae.blog.api.posting.dto.ResponseCreatePosting;
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

@Service
@RequiredArgsConstructor
public class PostingService {
    private static final String SORT_BY = "createdAt";

    private final PostingRepository postingRepository;
	private final PostingMapper postingMapper;


    @Transactional
    public ResponseCreatePosting create(RequestCreatePosting requestDto) {

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

    public Page<Posting> findPagePosting(RequestPagePosting requestDto) {
        Pageable pageable = requestDto.getPageable(Sort.by(SORT_BY));
        return postingRepository.findAll(pageable);
    }

    @Transactional
    public RequestCreatePosting findOnePosting(Long id){
        Posting posting = postingRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        return new RequestCreatePosting(posting);
    }

}
