package com.hanghae.blog.api.common.response;

import com.hanghae.blog.api.common.exception.ExceptionMessage;
import lombok.Getter;

@Getter
public class Response {

    private String msg;
    private int statusCode;

    public Response(ResponseMessage exceptionMessage) {
        this.msg = exceptionMessage.getMsg();
        this.statusCode = exceptionMessage.getStatus();
    }

    public Response(ExceptionMessage exceptionMessage) {
        this.msg = exceptionMessage.getMsg();
        this.statusCode = exceptionMessage.getStatus();
    }

    public Response(String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}
