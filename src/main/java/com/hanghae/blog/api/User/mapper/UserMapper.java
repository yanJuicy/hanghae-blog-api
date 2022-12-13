package com.hanghae.blog.api.User.mapper;

import com.hanghae.blog.api.User.dto.RequestCreateUser;
import com.hanghae.blog.api.User.entity.User;
import com.hanghae.blog.api.User.entity.UserRoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    //저희는 엔터티 -> dto 변환을 mapper 클래스에서 하기로 했습니다!
    @Value("${admin.token}")
    private String ADMIN_TOKEN;

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
