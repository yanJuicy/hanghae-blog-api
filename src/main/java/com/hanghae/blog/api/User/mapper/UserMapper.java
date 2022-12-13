package com.hanghae.blog.api.User.mapper;

import com.hanghae.blog.api.User.dto.RequestCreateUser;
import com.hanghae.blog.api.User.entity.User;
import com.hanghae.blog.api.User.entity.UserRoleEnum;
import com.hanghae.blog.api.common.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.hanghae.blog.api.common.response.ResponseMessage.SIGNUP_USER_SUCCESS_MSG;

@Component
@RequiredArgsConstructor
public class UserMapper {

    @Value("${admin.token}")
    private String ADMIN_TOKEN;

    public Response toResponse() {
        return new Response(SIGNUP_USER_SUCCESS_MSG);
    }

    public User toUser(RequestCreateUser requestCreateUser) {
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestCreateUser.isAdmin()) {
            if (!requestCreateUser.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }
        return new User(
                requestCreateUser.getUsername(),
                requestCreateUser.getPassword(),
                role
        );
    }
}
