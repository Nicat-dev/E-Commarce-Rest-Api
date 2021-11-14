package com.example.ecommarcerestapi.security.service;

import com.example.ecommarcerestapi.exception.DomainNotFoundException;
import com.example.ecommarcerestapi.repository.UserRepository;
import com.example.ecommarcerestapi.security.model.JwtUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Search user by username : {}",username);
        return userRepository.findUserByUserName(username)
                .map(JwtUser::new)
                .orElseThrow(
                        ()-> new DomainNotFoundException("User not found exception")
                );
    }
}
