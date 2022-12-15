package com.hanghae.blog.api.user.mapper;

import com.hanghae.blog.api.user.dto.RequestCreateUser;
import com.hanghae.blog.api.user.entity.User;
import com.hanghae.blog.api.user.entity.UserRoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static com.hanghae.blog.api.common.exception.ExceptionMessage.ADMIN_TOKEN_MISMATCH_ERROR_MSG;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;
    //저희는 엔터티 -> dto 변환을 mapper 클래스에서 하기로 했습니다!
    @Value("${admin.token}")
    private String ADMIN_TOKEN;

    public User toUser(RequestCreateUser requestCreateUser) {
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestCreateUser.isAdmin()) {
            if (!requestCreateUser.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException(ADMIN_TOKEN_MISMATCH_ERROR_MSG.getMsg());
            }
            role = UserRoleEnum.ADMIN;
        }
        return new User(
                requestCreateUser.getUsername(),
                passwordEncoder.encode(requestCreateUser.getPassword()),
                role
        );
    }
}
