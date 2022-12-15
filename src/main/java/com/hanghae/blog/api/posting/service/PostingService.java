package com.hanghae.blog.api.posting.service;

import com.hanghae.blog.api.comment.dto.ResponseComment;
import com.hanghae.blog.api.comment.entity.Comment;
import com.hanghae.blog.api.comment.mapper.CommentMapper;
import com.hanghae.blog.api.posting.dto.RequestCreatePosting;
import com.hanghae.blog.api.posting.dto.RequestPagePosting;
import com.hanghae.blog.api.posting.dto.ResponseOnePosting;
import com.hanghae.blog.api.posting.dto.ResponsePosting;
import com.hanghae.blog.api.posting.entity.Posting;
import com.hanghae.blog.api.posting.mapper.PostingMapper;
import com.hanghae.blog.api.posting.repository.PostingRepository;
import com.hanghae.blog.api.user.entity.User;
import com.hanghae.blog.api.user.repository.UserRepository;
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
    private final CommentMapper commentMapper;
    private final UserRepository userRepository;

    @Transactional
    public ResponsePosting create(String username, RequestCreatePosting requestDto) {
        Optional<User> user = userRepository.findByUsername(username);
		Posting posting = postingMapper.toPosting(user.get(), requestDto);

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
    public ResponseOnePosting findOnePosting(Long id){
        Posting posting = postingRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(NO_EXIST_POSTING_EXCEPTION_MSG.getMsg()));


        List<List<ResponseComment>> comments = new ArrayList<>();
        List<Comment> commentList = posting.getCommentList();
        if(commentList.size() != 0){
            for(Comment c : commentList){
                if(c.getCommentDepth() == 0) {
                    List<ResponseComment> result = commentMapper.toResponseCommentList(c);
                    comments.add(result);
                }
            }
        };

        return postingMapper.toResponseOnePosting(posting, comments);
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
