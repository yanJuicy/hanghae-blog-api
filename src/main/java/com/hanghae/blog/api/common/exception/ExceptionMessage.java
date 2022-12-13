package com.hanghae.blog.api.common.exception;

import lombok.Getter;

@Getter
public enum ExceptionMessage {

    // common
    INTERNAL_SERVER_ERROR_MSG(500,"서버 에러입니다."),
    NO_EXIST_COMMENT_EXCEPTION_MSG(400, "해당 댓글이 존재하지 않습니다.");
    private final int status;
    private final String msg;



    ExceptionMessage(final int status, final String msg) {
        this.status = status;
        this.msg = msg;
    }
}
