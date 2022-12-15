package com.hanghae.blog.api.posting.service;

import com.hanghae.blog.api.posting.dto.RequestCreatePosting;
import com.hanghae.blog.api.posting.dto.RequestPagePosting;
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
import java.util.Optional;

import static com.hanghae.blog.api.common.exception.ExceptionMessage.NO_EXIST_POSTING_EXCEPTION_MSG;
import static com.hanghae.blog.api.common.exception.ExceptionMessage.POSTING_TOKEN_ERROR_MSG;

@Service
@RequiredArgsConstructor
public class PostingService {
    private static final String SORT_BY = "createdAt";

    private final PostingRepository postingRepository;
	private final PostingMapper postingMapper;


    @Transactional
    public ResponsePosting create(RequestCreatePosting requestDto) {

		Posting posting = postingMapper.toPosting(requestDto);

        Posting savedPosting = postingRepository.save(posting);

        return postingMapper.toResponse(savedPosting);
    }

    @Transactional
    public List<ResponsePosting> findAllPosting(){
        List<Posting> Posting = postingRepository.findAll();

        List<ResponsePosting> result = new ArrayList<>();

        for(Posting p : Posting){
            result.add(postingMapper.toResponse(p));
        }
        return result;
    }

    public Page<Posting> findPagePosting(RequestPagePosting requestDto) {
        Pageable pageable = requestDto.getPageable(Sort.by(SORT_BY));
        return postingRepository.findAll(pageable);
    }

    @Transactional
    public ResponsePosting findOnePosting(Long id){
        Posting posting = postingRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(NO_EXIST_POSTING_EXCEPTION_MSG.getMsg()));

        return postingMapper.toResponse(posting);
    }
    @Transactional
    public ResponsePosting updatePosting(Long postingId, RequestCreatePosting requestCreatePosting){
        Optional<Posting> optional = postingRepository.findById(postingId);
        Posting posting = optional.orElseThrow(
                () -> new IllegalArgumentException(POSTING_TOKEN_ERROR_MSG.getMsg())
        );
        posting.setContents(requestCreatePosting.getContents());

        return postingMapper.toResponse(posting);
    }

    @Transactional
    public String deletePosting(Long postingId){
        postingRepository.findById(postingId)
                .orElseThrow(
                        () -> new IllegalArgumentException(POSTING_TOKEN_ERROR_MSG.getMsg()));
        postingRepository.deleteById(postingId);
        return "success";
    }

}
