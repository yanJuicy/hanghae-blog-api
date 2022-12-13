package com.hanghae.blog.api.common.exception;

import lombok.Getter;

@Getter
public enum ExceptionMessage {

    // common
    INTERNAL_SERVER_ERROR_MSG(500,"서버 에러입니다."),
    ADMIN_TOKEN_MISMATCH_ERROR_MSG(400,"관리자 암호가 틀려 등록이 불가능합니다."),
    DUPLICATE_USER_ERROR_MSG(400,"중복된 사용자가 존재합니다.");

    private final int status;
    private final String msg;

    ExceptionMessage(final int status, final String msg) {
        this.status = status;
        this.msg = msg;
    }
}
