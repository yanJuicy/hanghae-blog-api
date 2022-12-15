package com.hanghae.blog.api.posting.service;

import com.hanghae.blog.api.category.entity.Category;
import com.hanghae.blog.api.category.repository.CategoryRepository;
import com.hanghae.blog.api.category.service.CategoryService;
import com.hanghae.blog.api.category_posting_map.entity.CategoryPostingMap;
import com.hanghae.blog.api.category_posting_map.repository.CategoryPostingMapRepository;
import com.hanghae.blog.api.category_posting_map.service.CategoryPostingMapService;
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
import java.util.stream.Collectors;
import java.util.Optional;

import static com.hanghae.blog.api.common.exception.ExceptionMessage.NO_EXIST_POSTING_EXCEPTION_MSG;
import static com.hanghae.blog.api.common.exception.ExceptionMessage.POSTING_TOKEN_ERROR_MSG;

@Service
@RequiredArgsConstructor
public class PostingService {
    private static final String SORT_BY = "createdAt";

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

        return postingMapper.toResponse(savedPosting, requestDto.getCategories());
    }

    @Transactional
    public List<ResponsePosting> findAllPosting() {
        List<Posting> postingList = postingRepository.findAll();

        List<ResponsePosting> result = new ArrayList<>();

        for (Posting p : postingList) {
            List<String> categories = findCategories(p);
            result.add(postingMapper.toResponse(p, categories));
        }
        return result;
    }

    public Page<Posting> findPagePosting(RequestPagePosting requestDto) {
        Pageable pageable = requestDto.getPageable(Sort.by(SORT_BY));
        return postingRepository.findAll(pageable);
    }

    @Transactional
    public ResponsePosting findOnePosting(Long id) {
        Posting posting = postingRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(NO_EXIST_POSTING_EXCEPTION_MSG.getMsg()));
        List<String> categories = findCategories(posting);

        return postingMapper.toResponse(posting, categories);
    }

    @Transactional
    public ResponsePosting updatePosting(Long postingId, RequestCreatePosting requestCreatePosting) {
        Optional<Posting> optional = postingRepository.findById(postingId);
        Posting posting = optional.orElseThrow(
                () -> new IllegalArgumentException(POSTING_TOKEN_ERROR_MSG.getMsg())
        );
        posting.setContents(requestCreatePosting.getContents());

        List<String> categories = findCategories(posting);
        return postingMapper.toResponse(posting, categories);
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
            responseList.add(postingMapper.toResponse(posting, categoriesInPosing));
        }

        return responseList;
    }
}
