package com.example.ecommarcerestapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    @NotEmpty(message = "user name not be blank")
    private String userName;
    @NotEmpty(message = "user first name not be blank")
    private String userFirstName;
    @NotEmpty(message = "user surname not be blank")
    private String userSurname;
    @NotBlank(message = "user email adress not be blank")
    private String userEmail;
    @NotBlank(message = "user password can not be blank")
    private String userPassword;
    @NotBlank(message = "user address not be blank")
    private String userAdress;
    @NotBlank(message = "user age not be blank")
    private Integer userAge;
    @NotBlank(message = "user age not be blank")
    @Min(value = 15,message = "user age not be under the 15")
    private Integer userGeneder;
}
