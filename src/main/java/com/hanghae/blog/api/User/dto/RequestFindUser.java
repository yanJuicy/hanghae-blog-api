package com.hanghae.blog.api.User.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class RequestFindUser {
    private String username;
    private String password;
}
