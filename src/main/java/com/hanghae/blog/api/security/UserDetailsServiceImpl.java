package com.hanghae.blog.api.security;

import com.hanghae.blog.api.user.entity.User;
import com.hanghae.blog.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.hanghae.blog.api.common.exception.ExceptionMessage.USER_NOT_FOUND_ERROR_MSG;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_ERROR_MSG.getMsg()));

        return new UserDetailsImpl(user, user.getUsername());
    }
}
