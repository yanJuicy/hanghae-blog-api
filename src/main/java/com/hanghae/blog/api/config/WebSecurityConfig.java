package com.hanghae.blog.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanghae.blog.api.common.exception.ExceptionResponse;
import com.hanghae.blog.api.jwt.JwtAuthFilter;
import com.hanghae.blog.api.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.hanghae.blog.api.common.exception.ExceptionMessage.DO_NOT_HAVE_PERMISSION_ERROR_MSG;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig {
    private final JwtUtil jwtUtil;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web)->web.ignoring()
                .requestMatchers(PathRequest.toH2Console())
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/user/**").permitAll()
                .antMatchers(HttpMethod.GET, new String[]{"/api/postings/list", "/api/postings/{id}"}).permitAll()
                .antMatchers(HttpMethod.GET,"/api/postings").permitAll()
                .anyRequest().authenticated()
                //403 Forbidden 예외처리
                .and().exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint)
                .and().addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);



        return http.build();
    }

    //403 Forbidden 예외처리
    private final AuthenticationEntryPoint unauthorizedEntryPoint =
            (request, response, authException) -> {
                response.setStatus(DO_NOT_HAVE_PERMISSION_ERROR_MSG.getStatus());
                String json = new ObjectMapper().writeValueAsString(new ExceptionResponse(DO_NOT_HAVE_PERMISSION_ERROR_MSG));
                response.setContentType("application/json; charset=utf8");
                response.getWriter().write(json);
            };

}
