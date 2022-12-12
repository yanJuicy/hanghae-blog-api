package com.hanghae.blog.api.common.exception;

import com.hanghae.blog.api.common.response.Response;
import lombok.Getter;

@Getter
public class ExceptionResponse extends Response {

    public ExceptionResponse(ExceptionMessage message) {
        super(message);
    }

    public ExceptionResponse(String msg, int statusCode) {
        super(msg, statusCode);
    }
}
