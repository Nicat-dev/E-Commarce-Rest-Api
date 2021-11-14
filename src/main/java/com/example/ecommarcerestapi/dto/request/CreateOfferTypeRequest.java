package com.example.ecommarcerestapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOfferTypeRequest {
    @NotBlank(message = "offer type name not be blank")
    @Size(min = 3,message = "offer type name more than 3 letter")
    private String offerTypeName;
    @NotBlank(message = "offer type videoCard not be blank")
    @Min(value = 1,message = "video card not be negative")
    private Integer videoCard;
    @NotBlank(message = "offer size not be blank")
    @Size(min = 1,message = "offer size not be under the zero")
    private String offerSize;
    @NotBlank(message = "offer cpu not be blank")
    @Size(min = 1,message = "offer cpu must be more than zero")
    private String offerCpu;
    @NotBlank(message = "offer rem not be blank")
    @Size(min = 1,max = 32,message = "offer rem must be more than 0 and under the 32")
    private Integer rem;
    @NotBlank(message = "offer ram not be blank")
    @Size(min = 1,message = "offer must be 1 or more")
    private Integer ram;
    @NotBlank(message = "offer battery life not be blank")
    @Size(min = 1,message = "offer battery life ")
    private String offerBatteryLife;
    @NotBlank(message = "offer adapter voltage")
    private String adapterVoltage;
}
