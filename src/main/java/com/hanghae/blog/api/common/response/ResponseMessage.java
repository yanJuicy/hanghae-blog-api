package com.hanghae.blog.api.common.response;

import lombok.Getter;

@Getter
public enum ResponseMessage {

    // posting
    READ_POSTING_SUCCESS_MSG(200, "포스팅 정보 조회 성공");

    private final int status;
    private final String msg;
    ResponseMessage(final int status, final String msg) {
        this.status = status;
        this.msg = msg;
    }
}
