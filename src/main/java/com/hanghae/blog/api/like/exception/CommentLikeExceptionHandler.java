package com.hanghae.blog.api.like.exception;

import com.hanghae.blog.api.common.exception.ExceptionResponse;
import com.hanghae.blog.api.like.controller.CommentLikeController;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(assignableTypes = CommentLikeController.class)
public class CommentLikeExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ExceptionResponse handleBadRequest(Exception e){
        return new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
    }
}
