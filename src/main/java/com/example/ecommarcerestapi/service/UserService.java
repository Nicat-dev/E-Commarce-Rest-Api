package com.example.ecommarcerestapi.service;

import com.example.ecommarcerestapi.dto.request.CreateUserRequest;
import com.example.ecommarcerestapi.dto.request.UpdateUserRequest;
import com.example.ecommarcerestapi.model.User;

import java.util.List;

public interface UserService {
    void create(CreateUserRequest userRequest);
    User findUserById(Long id);
    List<User> findAll();
    void delete(Long id);
    boolean doesExistByUserName(String userName);
    void update(UpdateUserRequest userRequest,Long id);
}
