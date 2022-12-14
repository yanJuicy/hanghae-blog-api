package com.hanghae.blog.api.common.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Response {

    private String msg;
    private int statusCode;

    public Response(ResponseMessage exceptionMessage) {
        this.msg = exceptionMessage.getMsg();
        this.statusCode = exceptionMessage.getStatus();
    }

    public Response(String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}
