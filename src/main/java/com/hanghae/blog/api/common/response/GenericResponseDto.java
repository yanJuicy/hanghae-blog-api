package com.hanghae.blog.api.common.response;

import lombok.Getter;

@Getter
public class GenericResponseDto<T> {

    private String msg;
    private int statusCode;
	private T data;

    public GenericResponseDto(ResponseMessage responseMessage, T data) {
        this.msg = responseMessage.getMsg();
        this.statusCode = responseMessage.getStatus();
		this.data = data;
    }

}
