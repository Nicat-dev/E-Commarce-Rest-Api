package com.example.ecommarcerestapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOfferRequest {
    @NotEmpty(message = "offer name")
    @Size(min = 2,message = "offer name more than 2 letter")
    private String offerName;
    @NotEmpty(message = "offer type Id not be blank")
    @Size(min = 1,message = "offer type not be under the zero")
    private Long offerTypeId;
}
