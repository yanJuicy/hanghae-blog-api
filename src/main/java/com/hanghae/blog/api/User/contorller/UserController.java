package com.hanghae.blog.api.User.contorller;

import com.hanghae.blog.api.User.dto.RequestCreateUser;
import com.hanghae.blog.api.User.dto.RequestFindUser;
import com.hanghae.blog.api.User.service.UserService;
import com.hanghae.blog.api.common.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public Response signup(@RequestBody @Valid RequestCreateUser requestCreateUser) {
        return userService.signup(requestCreateUser);
    }

    @PostMapping("/login")
    public Response login(RequestFindUser requestFindUser, HttpServletResponse response) {
        return userService.login(requestFindUser, response);
    }
}
