package com.hanghae.blog.api.comment.exception;

import com.hanghae.blog.api.comment.controller.CommentController;
import com.hanghae.blog.api.common.response.Response;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(assignableTypes = CommentController.class)
public class CommentExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NullPointerException.class)
    public Response handleNullPointer(Exception e){
        return new Response(e.getMessage(), HttpStatus.NOT_FOUND.value());
    }

}
