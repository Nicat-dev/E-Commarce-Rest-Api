package com.example.ecommarcerestapi.mapper;

import com.example.ecommarcerestapi.dto.request.CreateOfferTypeRequest;
import com.example.ecommarcerestapi.dto.response.OfferTypeResponse;
import com.example.ecommarcerestapi.model.OfferType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OfferTypeMapper {
    public OfferType toEntity(CreateOfferTypeRequest from){
        return OfferType.builder()
                .ram(from.getRam())
                .offerTypeName(from.getOfferTypeName())
                .offerCpu(from.getOfferCpu())
                .offerSize(from.getOfferSize())
                .offerBatteryLife(from.getOfferBatteryLife())
                .rem(from.getRem())
                .videoCard(from.getVideoCard())
                .adapterVoltage(from.getAdapterVoltage())
                .build();

    }

    public OfferTypeResponse toDto(OfferType from){
        return OfferTypeResponse.builder()
                .offerTypeId(from.getOfferTypeId())
                .offerSize(from.getOfferSize())
                .offerCpu(from.getOfferCpu())
                .offerBatteryLife(from.getOfferBatteryLife())
                .offerTypeName(from.getOfferTypeName())
                .ram(from.getRam())
                .rem(from.getRem())
                .videoCard(from.getVideoCard())
                .createdAt(from.getCreatedAt())
                .updatedAt(from.getUpdatedAt())
                .status(from.getStatus())
                .build();
    }
}
