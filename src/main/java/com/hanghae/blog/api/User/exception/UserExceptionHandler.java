package com.hanghae.blog.api.User.exception;


import com.hanghae.blog.api.User.contorller.UserController;
import com.hanghae.blog.api.common.response.Response;
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

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(assignableTypes = UserController.class)
public class UserExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class})
    public Response handleBadRequest(IllegalArgumentException e) {

        if (e.getMessage().equals(ADMIN_TOKEN_MISMATCH_ERROR_MSG.getMsg())) {
            return new Response(ADMIN_TOKEN_MISMATCH_ERROR_MSG);
        } else if (e.getMessage().equals(DUPLICATE_USER_ERROR_MSG.getMsg())) {
            return new Response(DUPLICATE_USER_ERROR_MSG);
        }
        return new Response(INTERNAL_SERVER_ERROR_MSG);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Response handleBadRequest(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            if (fieldError.getField().equals("username")) {
                return new Response(fieldError.getField() + "(은)는 최소 4 자 및 최대 10 자 하나의 소문자, 하나의 숫자가 들어가야합니다.", 400);
            } else {
                return new Response(fieldError.getField() + "최소 8 자 및 최대 15 자, 하나 이상의 대문자, 하나의 소문자, 하나의 숫자 및 하나의 특수 문자가 들어가야합니다.", 400);
            }
        }
        return new Response(INTERNAL_SERVER_ERROR_MSG);

    }
}
