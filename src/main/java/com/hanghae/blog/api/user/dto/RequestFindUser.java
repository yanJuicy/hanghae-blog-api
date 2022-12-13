package com.hanghae.blog.api.user.dto;

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
