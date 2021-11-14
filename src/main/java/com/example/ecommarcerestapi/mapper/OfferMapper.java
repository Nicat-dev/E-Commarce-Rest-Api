package com.example.ecommarcerestapi.mapper;

import com.example.ecommarcerestapi.dto.request.CreateOfferRequest;
import com.example.ecommarcerestapi.dto.response.OfferResponse;
import com.example.ecommarcerestapi.model.Offer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OfferMapper {

    public Offer toEntity(CreateOfferRequest from){
        return Offer.builder()
                .offerName(from.getOfferName())
                .offerName(from.getOfferName())
                .build();
    }

    public OfferResponse toDto(Offer from){
        return OfferResponse.builder()
                .offerId(from.getOfferId())
                .offerType(from.getOfferType().getOfferTypeId())
                .offerName(from.getOfferName())
                .createdAt(from.getCreatedAt())
                .updatedAt(from.getUpdatedAt())
                .status(from.getStatus())
                .build();
    }


}
