package com.hanghae.blog.api.domain.posting.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@AllArgsConstructor
@Getter
@Setter
public class RequestPagePosting {

    private int page;
    private int size;

    public RequestPagePosting() {
        this.page = 1;
        this.size = 1;
    }

    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page - 1, size, sort);
    }
}
