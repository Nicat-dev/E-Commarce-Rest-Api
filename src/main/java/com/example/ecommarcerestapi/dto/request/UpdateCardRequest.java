package com.example.ecommarcerestapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCardRequest {
    @NotEmpty(message = "card number can't be empty")
    private String cardNumber;
    @NotEmpty(message = "card code can't be empty")
    private String cardCode;
    @NotEmpty(message = "card pin can't be null")
    private String cardPin;
    @NotEmpty(message = "card balance is not empty")
    @Min(value = 0,message = "card balance cant be under the zero")
    private Float cardBalance;
}
