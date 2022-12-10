package com.hanghae.blog.api.posting.exception;

import com.hanghae.blog.api.posting.controller.PostingController;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(assignableTypes = PostingController.class)
public class PostingExceptionHandler {
}



