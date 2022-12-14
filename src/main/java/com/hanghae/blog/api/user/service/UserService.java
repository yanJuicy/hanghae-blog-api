package com.hanghae.blog.api.user.service;

import com.hanghae.blog.api.jwt.JwtUtil;
import com.hanghae.blog.api.user.dto.RequestCreateUser;
import com.hanghae.blog.api.user.dto.RequestFindUser;
import com.hanghae.blog.api.user.entity.User;
import com.hanghae.blog.api.user.mapper.UserMapper;
import com.hanghae.blog.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.hanghae.blog.api.common.exception.ExceptionMessage.DUPLICATE_USER_ERROR_MSG;
import static com.hanghae.blog.api.common.exception.ExceptionMessage.PASSWORDS_DO_NOT_MATCH_ERROR_MSG;
import static com.hanghae.blog.api.common.exception.ExceptionMessage.USER_NOT_FOUND_ERROR_MSG;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;

    @Transactional
    public void signup(RequestCreateUser requestCreateUser) {
        User user = userMapper.toUser(requestCreateUser);

        // 회원 중복 확인
        Optional<User> found = userRepository.findByUsername(user.getUsername());
        if (found.isPresent()) {
            throw new IllegalArgumentException(DUPLICATE_USER_ERROR_MSG.getMsg());
        }

        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public void login(RequestFindUser requestFindUser, HttpServletResponse response) {
        User user=userRepository.findByUsername(requestFindUser.getUsername()).orElseThrow(
                ()->new IllegalArgumentException(USER_NOT_FOUND_ERROR_MSG.getMsg())
        );

        if (!passwordEncoder.matches(requestFindUser.getPassword(), user.getPassword())){
            throw new IllegalArgumentException(PASSWORDS_DO_NOT_MATCH_ERROR_MSG.getMsg());
        }
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(), user.getRole()));
    }

    @Transactional
    public void userDelete(String password, User user) {
        userRepository.findByUsername(user.getUsername()).orElseThrow(
                ()->new IllegalArgumentException(USER_NOT_FOUND_ERROR_MSG.getMsg())
        );
        System.out.println(user.getUsername());
        if (!passwordEncoder.matches(password, user.getPassword())){
            throw new IllegalArgumentException(PASSWORDS_DO_NOT_MATCH_ERROR_MSG.getMsg());
        }
        userRepository.delete(user);
    }
}
