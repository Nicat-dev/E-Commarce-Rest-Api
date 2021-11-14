package com.example.ecommarcerestapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOfferTypeRequest {
    @NotBlank(message = "offer type name can not be blank")
    private String offerTypeName;
    @NotBlank(message = "video card can not be blank")
    private Integer videoCard;
    @NotBlank(message = "rem can not be blank")
    private Integer rem;
    @NotBlank(message = "ram can not be blank")
    private Integer ram;
    @NotBlank(message = "offer size can not be blank")
    private String offerSize;
    @NotBlank(message = "offer cpu can not be blank")
    private String offerCpu;
    @NotBlank(message = "offer battery life can not be blank")
    private String offerBatteryLife;
    @NotBlank(message = "adapter voltage can not be blank")
    private String adapterVoltage;

}
