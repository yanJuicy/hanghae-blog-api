package com.hanghae.blog.api.posting.service;

import com.hanghae.blog.api.category.entity.Category;
import com.hanghae.blog.api.category.repository.CategoryRepository;
import com.hanghae.blog.api.category.service.CategoryService;
import com.hanghae.blog.api.category_posting_map.entity.CategoryPostingMap;
import com.hanghae.blog.api.category_posting_map.repository.CategoryPostingMapRepository;
import com.hanghae.blog.api.category_posting_map.service.CategoryPostingMapService;
import com.hanghae.blog.api.comment.dto.ResponseComment;
import com.hanghae.blog.api.comment.entity.Comment;
import com.hanghae.blog.api.comment.mapper.CommentMapper;
import com.hanghae.blog.api.comment.repository.CommentRepository;
import com.hanghae.blog.api.posting.dto.RequestCreatePosting;
import com.hanghae.blog.api.posting.dto.RequestPagePosting;
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
import java.util.stream.Collectors;

import static com.hanghae.blog.api.common.exception.ExceptionMessage.NO_EXIST_POSTING_EXCEPTION_MSG;
import static com.hanghae.blog.api.common.exception.ExceptionMessage.POSTING_TOKEN_ERROR_MSG;
import static com.hanghae.blog.api.common.exception.ExceptionMessage.USER_NOT_MATCH_ERROR_MSG;

@Service
@RequiredArgsConstructor
public class PostingService {
    private static final String SORT_BY = "createdAt";
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final PostingRepository postingRepository;
    private final PostingMapper postingMapper;
    private final UserRepository userRepository;
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;
    private final CategoryPostingMapService categoryPostingMapService;
    private final CategoryPostingMapRepository categoryPostingMapRepository;


    @Transactional
    public ResponsePosting create(String username, RequestCreatePosting requestDto) {
        Optional<User> user = userRepository.findByUsername(username);
        Posting posting = postingMapper.toPosting(user.get(), requestDto);


        Posting savedPosting = postingRepository.save(posting);

        // 카테고리 저장
        List<Category> categoryList = categoryService.saveCategories(requestDto.getCategories());

        // 카테고리_포스팅 매핑 테이블 저장
        categoryPostingMapService.saveCategoryPostingMap(categoryList, savedPosting);

        return postingMapper.toResponse(savedPosting, requestDto.getCategories(), null);
    }

    @Transactional(readOnly = true)
    public List<ResponsePosting> findAllPosting() {
        List<Posting> postingList = postingRepository.findAll();

        List<ResponsePosting> result = new ArrayList<>();

        for (Posting p : postingList) {
            List<Comment> comments = commentRepository.findAllByPostingOrderByCreatedAtDesc(p);
            List<ResponseComment> commentList = new ArrayList<>();
            for (Comment c : comments) {
                commentList.add(commentMapper.toResponse(c));
            }
            List<String> categories = findCategories(p);
            result.add(postingMapper.toResponse(p, categories, commentList));
        }
        return result;
    }

    public Page<Posting> findPagePosting(RequestPagePosting requestDto) {
        Pageable pageable = requestDto.getPageable(Sort.by(SORT_BY));
        return postingRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public ResponsePosting findOnePosting(Long id) {
        Posting posting = postingRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(NO_EXIST_POSTING_EXCEPTION_MSG.getMsg()));
        List<String> categories = findCategories(posting);

        List<Comment> comments = commentRepository.findAllByPostingOrderByCreatedAtDesc(posting);
        List<ResponseComment> commentList = new ArrayList<>();
        for (Comment c : comments) {
            commentList.add(commentMapper.toResponse(c));
        }

        return postingMapper.toResponse(posting, categories, commentList);
    }

    @Transactional
    public ResponsePosting updatePosting(String username, Long postingId, RequestCreatePosting requestCreatePosting){
        Optional<Posting> optional = postingRepository.findById(postingId);
        Posting posting = optional.orElseThrow(
                () -> new IllegalArgumentException(POSTING_TOKEN_ERROR_MSG.getMsg()));
        if(!posting.getUser().getUsername().equals(username)){
            throw new IllegalArgumentException(USER_NOT_MATCH_ERROR_MSG.getMsg());
        }
        posting.setContents(requestCreatePosting.getContents());
        List<String> categories = findCategories(posting);

        List<Comment> comments = commentRepository.findAllByPostingOrderByCreatedAtDesc(posting);
        List<ResponseComment> commentList = new ArrayList<>();
        for (Comment c : comments) {
            commentList.add(commentMapper.toResponse(c));
        }

        return postingMapper.toResponse(posting, categories, commentList);
    }

    public List<String> findCategories(Posting posting) {
        List<Category> categories = categoryPostingMapService.findCategories(posting);
        List<String> categoryNames = categories.stream()
                .map(c -> c.getName())
                .collect(Collectors.toList());
        return categoryNames;
    }

    @Transactional
    public String deletePosting(Long postingId) {
        postingRepository.findById(postingId)
                .orElseThrow(
                        () -> new IllegalArgumentException(POSTING_TOKEN_ERROR_MSG.getMsg()));
        postingRepository.deleteById(postingId);
        return "success";
    }

    @Transactional(readOnly = true)
    public List<ResponsePosting> findPostingsByCategory(String categoryName) {
        Optional<Category> foundCategory = categoryRepository.findByName(categoryName);
        if (foundCategory.isEmpty()) {
            return new ArrayList<>();
        }

        List<CategoryPostingMap> categoryPostingMapList = categoryPostingMapRepository.findAllByCategory(foundCategory.get());
        List<Posting> postingList = categoryPostingMapList.stream()
                .map(e -> e.getPosting())
                .collect(Collectors.toList());

        List<ResponsePosting> responseList = new ArrayList<>();


        for (Posting posting : postingList) {
            List<String> categoriesInPosing = findCategories(posting);
            List<ResponseComment> commentList = commentRepository.findAllByPostingOrderByCreatedAtDesc(posting)
                    .stream()
                    .map(c -> commentMapper.toResponse(c))
                    .collect(Collectors.toList());

            responseList.add(postingMapper.toResponse(posting, categoriesInPosing, commentList));
        }

        return responseList;
    }
}
