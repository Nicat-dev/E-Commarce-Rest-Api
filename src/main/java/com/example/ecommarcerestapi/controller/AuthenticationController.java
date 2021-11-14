package com.example.ecommarcerestapi.controller;

import com.example.ecommarcerestapi.dto.request.CreateUserRequest;
import com.example.ecommarcerestapi.dto.response.ApiMessage;
import com.example.ecommarcerestapi.exception.DomainExistException;
import com.example.ecommarcerestapi.security.dto.AuthRequest;
import com.example.ecommarcerestapi.security.dto.AuthResponse;
import com.example.ecommarcerestapi.security.service.AuthenticationService;
import com.example.ecommarcerestapi.service.UserService;
import com.example.ecommarcerestapi.util.ApiBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController implements ApiBuilder {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/signing")
    public ResponseEntity<AuthResponse> authenticateUser(@Valid @RequestBody AuthRequest loginRequest){
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(authenticationService.createAuthenticationToken(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiMessage> registerUser(@Valid @RequestBody CreateUserRequest signUpRequest){
            if (userService.doesExistByUserName(signUpRequest.getUserName()))
                throw new DomainExistException("Exist this domain following username := "+signUpRequest.getUserName());
            userService.create(signUpRequest);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(generateOkay());

        }
}
