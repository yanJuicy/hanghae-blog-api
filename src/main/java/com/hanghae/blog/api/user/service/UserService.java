package com.hanghae.blog.api.user.service;

import com.hanghae.blog.api.user.dto.RequestCreateUser;
import com.hanghae.blog.api.user.dto.RequestFindUser;
import com.hanghae.blog.api.user.entity.User;
import com.hanghae.blog.api.user.mapper.UserMapper;
import com.hanghae.blog.api.user.repository.UserRepository;
import com.hanghae.blog.api.common.response.Response;
import com.hanghae.blog.api.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.hanghae.blog.api.common.response.ResponseMessage.LOGIN_USER_SUCCESS_MSG;
import static com.hanghae.blog.api.common.response.ResponseMessage.SIGNUP_USER_SUCCESS_MSG;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;

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

    @Transactional(readOnly = true)
    public Response login(RequestFindUser requestFindUser, HttpServletResponse response) {
        User user=userRepository.findByUsername(requestFindUser.getUsername()).orElseThrow(
                ()->new IllegalArgumentException("회원을 찾을 수 없습니다!")
        );

        if (!user.getPassword().equals(requestFindUser.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다!");
        }
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(), user.getRole()));
        return new Response(LOGIN_USER_SUCCESS_MSG);
    }

}
