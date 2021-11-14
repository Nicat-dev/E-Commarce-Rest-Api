package com.example.ecommarcerestapi.security.service;

import com.example.ecommarcerestapi.exception.AuthenticationException;
import com.example.ecommarcerestapi.exception.TokenNotFoundException;
import com.example.ecommarcerestapi.security.dto.AuthRequest;
import com.example.ecommarcerestapi.security.dto.AuthResponse;
import com.example.ecommarcerestapi.security.model.JwtUser;
import com.example.ecommarcerestapi.security.util.JwtTokenUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Objects;

@Data
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;

    public AuthResponse createAuthenticationToken(AuthRequest request){
        authenticate(request.getUserName(), request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserName());
        String token = jwtTokenUtil.generateToken(userDetails);
        return new AuthResponse(token);
    }



    public JwtUser getUserByToken(String authToken){
        if(Objects.isNull(authToken) || authToken.length() < 7)
            throw new TokenNotFoundException("cant get user by token");

        String token = authToken.substring(7);

        String username = jwtTokenUtil.getUsernameFromToken(token);
        return (JwtUser) userDetailsService.loadUserByUsername(username);
    }

    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch (DisabledException e){
            throw new AuthenticationException("user is disabled");
        }catch (BadCredentialsException e){
            throw new AuthenticationException("bad credentials");
        }
    }
}
