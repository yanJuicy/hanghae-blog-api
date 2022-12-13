package com.hanghae.blog.api.common.exception;

import lombok.Getter;

@Getter
public class ExceptionResponse {

    private String msg;
    private int statusCode;

    public ExceptionResponse(ExceptionMessage exceptionMessage) {
        this.msg = exceptionMessage.getMsg();
        this.statusCode = exceptionMessage.getStatus();
    }

    public ExceptionResponse(String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}
