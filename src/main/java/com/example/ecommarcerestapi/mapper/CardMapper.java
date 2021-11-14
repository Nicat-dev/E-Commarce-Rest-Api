package com.example.ecommarcerestapi.mapper;

import com.example.ecommarcerestapi.dto.request.CreateCardRequest;
import com.example.ecommarcerestapi.dto.response.CradResponse;
import com.example.ecommarcerestapi.model.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardMapper {
    private CardMapper cardMapper;

    public Card toEntity(CreateCardRequest from){
        return Card.builder()
                .cardNumber(from.getCardNumber())
                .cardCode(from.getCardCode())
                .cardPin(from.getCardPin())
                .build();
    }


    public CradResponse toDto(Card from){
        return CradResponse.builder()
                .cardId(from.getCardId())
                .cardNumber(from.getCardNumber())
                .cardCode(from.getCardCode())
                .cardPin(from.getCardPin())
                .cardBalance(from.getCardBalance())
                .createdAt(from.getCreatedAt())
                .updatedAt(from.getUpdatedAt())
                .status(from.getStatus())
                .build();
    }
}
