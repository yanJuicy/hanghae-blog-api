package com.hanghae.blog.api.common.response;

import lombok.Getter;

@Getter
public enum ResponseMessage {

    // posting
    READ_POSTING_SUCCESS_MSG(200, "포스팅 정보 조회 성공"),
    CREATE_POSTING_SUCCESS_MSG(201, "포스팅 생성 성공 "),
    CREATE_COMMENT_SUCCESS_MSG(201, "댓글 생성 성공"),
    UPDATE_COMMENT_SUCCESS_MSG(200, "댓글 수정 성공");
    SIGNUP_USER_SUCCESS_MSG(201, "유저 회원가입 성공 "),;

    private final int status;
    private final String msg;
    ResponseMessage(final int status, final String msg) {
        this.status = status;
        this.msg = msg;
    }
}
