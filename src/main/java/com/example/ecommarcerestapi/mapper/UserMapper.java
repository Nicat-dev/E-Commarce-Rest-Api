package com.example.ecommarcerestapi.mapper;

import com.example.ecommarcerestapi.dto.request.CreateUserRequest;
import com.example.ecommarcerestapi.dto.response.UserResponse;
import com.example.ecommarcerestapi.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public User toEntity(CreateUserRequest from){
        return User.builder()
                .userName(from.getUserName())
                .userFirstName(from.getUserFirstName())
                .userSurname(from.getUserSurname())
                .userAdress(from.getUserAdress())
                .userEmail(from.getUserEmail())
                .userAge(from.getUserAge())
                .build();
    }

    public UserResponse toDto(User from){
        return UserResponse.builder()
                .userId(from.getUserId())
                .age(from.getUserAge())
                .gender(from.getStatus())
                .gmail(from.getUserEmail())
                .cratedAt(from.getCreatedAt())
                .userFirstName(from.getUserFirstName())
                .userPhoneNumber(from.getUserAdress())
                .userSurname(from.getUserSurname())
                .updatedAt(from.getUpdatedAt())
                .userName(from.getUserName())
                .build();
    }
}
