package com.hanghae.blog.api.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestFindUser {
    private String username;
    private String password;
}
