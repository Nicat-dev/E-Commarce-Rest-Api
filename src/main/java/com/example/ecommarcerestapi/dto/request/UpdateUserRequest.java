package com.example.ecommarcerestapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
    @NotBlank(message = "username can not be blank")
    private String userName;
    @NotBlank(message = "user first name can not be blank")
    private String userFirstName;
    @NotBlank(message = "user surname can not be blank")
    private String userSurname;
    @NotBlank(message = "user gmail can not be blank")
    private String userGmail;
    @NotBlank(message = "user password can not be blank")
    private String password;
    @NotBlank(message = "user address can not be blank")
    private String userAddress;
    @NotBlank(message = "user age can not be blank")
    private Integer age;
    @NotBlank(message = "user gender can not be blank")
    private Integer gender;
    @NotBlank(message = "user status can not be blank")
    private Integer status;
}
