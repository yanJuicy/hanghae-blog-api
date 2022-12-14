package com.hanghae.blog.api.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanghae.blog.api.common.exception.ExceptionMessage;
import com.hanghae.blog.api.common.exception.ExceptionResponse;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.hanghae.blog.api.common.exception.ExceptionMessage.TOKEN_ERROR_MSG;
import static com.hanghae.blog.api.common.exception.ExceptionMessage.USER_NOT_FOUND_ERROR_MSG;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;


    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = jwtUtil.resolveToken(request);

        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        if (!jwtUtil.validateToken(token)) {
            jwtExceptionHandler(response, TOKEN_ERROR_MSG);
        }
        Claims info = jwtUtil.getUserInfoFromToken(token);
        setAuthentication(response, info.getSubject());
        filterChain.doFilter(request, response);
    }

    public void setAuthentication(HttpServletResponse response, String username) {
        try {
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            Authentication authentication = jwtUtil.createAuthentication(username);
            context.setAuthentication(authentication);

            SecurityContextHolder.setContext(context);
        } catch (UsernameNotFoundException e) {
            jwtExceptionHandler(response, USER_NOT_FOUND_ERROR_MSG);
        }

    }

    public void jwtExceptionHandler(HttpServletResponse response, ExceptionMessage exceptionMessage) {
        //"application/json"에서
        //"application/json; charset=utf8" 변경시 한글 에러메세지 가능!
        response.setContentType("application/json; charset=utf8");
        try {
            String json = new ObjectMapper().writeValueAsString(new ExceptionResponse(exceptionMessage));
            response.getWriter().write(json);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
