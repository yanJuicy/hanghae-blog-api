package com.hanghae.blog.api.common.exception;

import lombok.Getter;

@Getter
public class ExceptionResponseDto {

    private String msg;
    private int statusCode;

    public ExceptionResponseDto(ExceptionMessage exceptionMessage) {
        this.msg = exceptionMessage.getMsg();
        this.statusCode = exceptionMessage.getStatus();
    }

    public ExceptionResponseDto(String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}
