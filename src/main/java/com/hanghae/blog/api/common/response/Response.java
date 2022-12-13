package com.hanghae.blog.api.common.response;

import lombok.Getter;

@Getter
public class Response {

    private String msg;
    private int statusCode;

    public Response(ResponseMessage responseMessage) {
        this.msg = responseMessage.getMsg();
        this.statusCode = responseMessage.getStatus();
    }

    public Response(String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}
