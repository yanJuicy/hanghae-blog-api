package com.hanghae.blog.api.common.response;

import lombok.Getter;

@Getter
public class DataResponse<T> extends Response {

    private T data;

    public DataResponse(ResponseMessage responseMessage, T data) {
        super(responseMessage);
        this.data = data;
    }

    public DataResponse(String msg, int statusCode, T data) {
        super(msg, statusCode);
        this.data = data;
    }
}
