package com.hanghae.blog.api.User.service;

import com.hanghae.blog.api.User.dto.RequestCreateUser;
import com.hanghae.blog.api.User.entity.User;
import com.hanghae.blog.api.User.mapper.UserMapper;
import com.hanghae.blog.api.User.repository.UserRepository;
import com.hanghae.blog.api.common.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.hanghae.blog.api.common.response.ResponseMessage.SIGNUP_USER_SUCCESS_MSG;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public Response signup(RequestCreateUser requestCreateUser) {
        User user = userMapper.toUser(requestCreateUser);

        // 회원 중복 확인
        Optional<User> found = userRepository.findByUsername(user.getUsername());
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        userRepository.save(user);
        return new Response(SIGNUP_USER_SUCCESS_MSG);
    }
}
