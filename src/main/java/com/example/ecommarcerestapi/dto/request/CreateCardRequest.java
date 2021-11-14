package com.example.ecommarcerestapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCardRequest {
    @NotEmpty(message = "card number can't be empty")
    @Size(min = 16,max = 16,message = "card number must be 16 digits")
    private String cardNumber;
    @NotEmpty(message = "card code can't be empty")
    @Size(min = 4,message = "card code digits more than 4")
    private String cardCode;
    @NotEmpty(message = "card pin can't be null")
    @Size(min = 3,max = 3,message = "card pin is 3 digits")
    private String cardPin;
    @NotEmpty(message = "card balance is not empty")
    @Size(min = 0,message = "balance cant be under the zero")
    private Float cardBalance;

}
