package com.example.ecommarcerestapi.controller;

import com.example.ecommarcerestapi.dto.request.CreateUserRequest;
import com.example.ecommarcerestapi.dto.request.UpdateUserRequest;
import com.example.ecommarcerestapi.dto.response.ApiMessage;
import com.example.ecommarcerestapi.dto.response.UserResponse;
import com.example.ecommarcerestapi.mapper.UserMapper;
import com.example.ecommarcerestapi.service.UserService;
import com.example.ecommarcerestapi.util.ApiBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController implements ApiBuilder {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<ApiMessage> create(@Valid @RequestBody CreateUserRequest request){
        log.info("Service for creation user accepted");
        userService.create(request);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(generateOkay());
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll(){
        List<UserResponse> userResponses = userService.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userResponses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiMessage> update(@Valid @RequestBody UpdateUserRequest request,@PathVariable Long id){
        userService.update(request,id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(generateOkay());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiMessage> deleteById(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(generateOkay());
    }

}
