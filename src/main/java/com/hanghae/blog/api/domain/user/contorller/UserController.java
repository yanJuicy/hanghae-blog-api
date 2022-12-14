package com.hanghae.blog.api.domain.user.contorller;

import com.hanghae.blog.api.common.response.Response;
import com.hanghae.blog.api.security.UserDetailsImpl;
import com.hanghae.blog.api.domain.user.dto.RequestCreateUser;
import com.hanghae.blog.api.domain.user.dto.RequestFindUser;
import com.hanghae.blog.api.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static com.hanghae.blog.api.common.response.ResponseMessage.LOGIN_USER_SUCCESS_MSG;
import static com.hanghae.blog.api.common.response.ResponseMessage.SIGNUP_USER_SUCCESS_MSG;
import static com.hanghae.blog.api.common.response.ResponseMessage.USER_DELETE_SUCCESS_MSG;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public Response signup(@RequestBody @Valid RequestCreateUser requestCreateUser) {
        userService.signup(requestCreateUser);
        return new Response(SIGNUP_USER_SUCCESS_MSG);
    }

    @PostMapping("/login")
    public Response login(@RequestBody RequestFindUser requestFindUser, HttpServletResponse response) {
        userService.login(requestFindUser, response);
        return new Response(LOGIN_USER_SUCCESS_MSG);
    }

    @DeleteMapping("/delete")
    public Response userDelete(@RequestBody RequestFindUser requestFindUser, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        userService.userDelete(requestFindUser.getPassword(), userDetails.getUser());
        return new Response(USER_DELETE_SUCCESS_MSG);
    }
}
