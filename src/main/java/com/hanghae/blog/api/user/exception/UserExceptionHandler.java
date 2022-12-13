package com.hanghae.blog.api.user.exception;


import com.hanghae.blog.api.user.contorller.UserController;
import com.hanghae.blog.api.common.exception.ExceptionResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.hanghae.blog.api.common.exception.ExceptionMessage.ADMIN_TOKEN_MISMATCH_ERROR_MSG;
import static com.hanghae.blog.api.common.exception.ExceptionMessage.DUPLICATE_USER_ERROR_MSG;
import static com.hanghae.blog.api.common.exception.ExceptionMessage.INTERNAL_SERVER_ERROR_MSG;
import static com.hanghae.blog.api.common.exception.ExceptionMessage.PASSWORDS_DO_NOT_MATCH_ERROR_MSG;
import static com.hanghae.blog.api.common.exception.ExceptionMessage.USER_NOT_FOUND_ERROR_MSG;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(assignableTypes = UserController.class)
public class UserExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class})
    public ExceptionResponse handleBadRequest(IllegalArgumentException e) {

        if (e.getMessage().equals(ADMIN_TOKEN_MISMATCH_ERROR_MSG.getMsg())) {
            return new ExceptionResponse(ADMIN_TOKEN_MISMATCH_ERROR_MSG);
        }
        if (e.getMessage().equals(DUPLICATE_USER_ERROR_MSG.getMsg())) {
            return new ExceptionResponse(DUPLICATE_USER_ERROR_MSG);
        }
        if (e.getMessage().equals(USER_NOT_FOUND_ERROR_MSG.getMsg())) {
            return new ExceptionResponse(USER_NOT_FOUND_ERROR_MSG);
        }
        if (e.getMessage().equals(PASSWORDS_DO_NOT_MATCH_ERROR_MSG.getMsg())) {
            return new ExceptionResponse(PASSWORDS_DO_NOT_MATCH_ERROR_MSG);
        }
        return new ExceptionResponse(INTERNAL_SERVER_ERROR_MSG);
    }

    //@Valid 예외처리
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ExceptionResponse handleBadRequest(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            if (fieldError.getField().equals("username")) {
                return new ExceptionResponse(fieldError.getField() + "(은)는 최소 4 자 및 최대 10 자 하나의 소문자, 하나의 숫자가 들어가야합니다.", 400);
            }
            return new ExceptionResponse(fieldError.getField() + "최소 8 자 및 최대 15 자, 하나 이상의 대문자, 하나의 소문자, 하나의 숫자 및 하나의 특수 문자가 들어가야합니다.", 400);
        }
        return new ExceptionResponse(INTERNAL_SERVER_ERROR_MSG);

    }
}
