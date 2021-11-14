package com.example.ecommarcerestapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOfferRequest {
    @NotBlank(message = "offer name can not be blank")
    private String offerName;
    @NotBlank(message = "offer type id can not be blank")
    private Long offerTypeId;
}
