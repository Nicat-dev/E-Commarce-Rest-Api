package com.example.ecommarcerestapi.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class OfferTypeResponse {
    private Long offerTypeId;
    private String offerTypeName;
    private Integer videoCard;
    private Integer rem;
    private Integer ram;
    private String offerSize;
    private String offerCpu;
    private String offerBatteryLife;
    private String adapterVoltage;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime updatedAt;
    private Integer status;
}
