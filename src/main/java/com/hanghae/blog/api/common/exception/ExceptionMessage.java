package com.hanghae.blog.api.common.exception;

import lombok.Getter;

@Getter
public enum ExceptionMessage {

    // common
    INTERNAL_SERVER_ERROR_MSG(500,"서버 에러입니다."),

    //user
    ADMIN_TOKEN_MISMATCH_ERROR_MSG(400,"관리자 암호가 틀려 등록이 불가능합니다."),
    DUPLICATE_USER_ERROR_MSG(400,"중복된 사용자가 존재합니다."),
    USER_NOT_FOUND_ERROR_MSG(400,"회원을 찾을 수 없습니다!"),
    PASSWORDS_DO_NOT_MATCH_ERROR_MSG(400,"비밀번호가 일치하지 않습니다!"),
    
    //jwt
    TOKEN_ERROR_MSG(401,"토큰이 유효하지 않습니다."),
    DO_NOT_HAVE_PERMISSION_ERROR_MSG(403,"사용 권한이 없습니다."),

    // posting
    NO_EXIST_POSTING_EXCEPTION_MSG(404, "해당 게시글이 존재하지 않습니다."),


    // comment
    NO_EXIST_COMMENT_EXCEPTION_MSG(400, "해당 댓글이 존재하지 않습니다.");


    private final int status;
    private final String msg;

    ExceptionMessage(final int status, final String msg) {
        this.status = status;
        this.msg = msg;
    }
}
