package com.hanghae.blog.api.common.response;

import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
public class PageResponse<DTO, EN> extends Response {
    private static final int DISPLAY_PAGES = 2;

    private List<DTO> data;
    private int totalPage;
    private int page;
    private int size;
    private int start, end;
    private boolean prev, next;
    private List<Integer> pageList;

    public PageResponse(ResponseMessage responseMessage, Page<EN> result, Function<EN, DTO> fn) {
        super(responseMessage);
        data = result.stream()
                .map(fn)
                .collect(Collectors.toList());

        totalPage = result.getTotalPages();

        makePageList(result.getPageable());
    }

    private void makePageList(Pageable pageable) {
        this.page = pageable.getPageNumber() + 1;
        this.size = pageable.getPageSize();

        int tempEnd = (int) (Math.ceil(page / 10.0)) * DISPLAY_PAGES;

        start = tempEnd - DISPLAY_PAGES + 1;
        end = totalPage > tempEnd ? tempEnd : totalPage;

        prev = start > 1;
        next = totalPage > tempEnd;

        pageList = IntStream.rangeClosed(start, end)
                .boxed()
                .collect(Collectors.toList());
    }

}
