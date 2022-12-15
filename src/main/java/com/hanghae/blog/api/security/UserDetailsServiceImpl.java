package com.hanghae.blog.api.security;

import com.hanghae.blog.api.domain.user.repository.UserRepository;
import com.hanghae.blog.api.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.hanghae.blog.api.common.exception.ExceptionMessage.USER_DOES_NOT_EXIST_ERROR_MSG;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(USER_DOES_NOT_EXIST_ERROR_MSG.getMsg()));

        return new UserDetailsImpl(user, user.getUsername());
    }
}
