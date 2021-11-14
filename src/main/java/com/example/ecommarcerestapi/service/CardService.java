package com.example.ecommarcerestapi.service;

import com.example.ecommarcerestapi.dto.request.CreateCardRequest;
import com.example.ecommarcerestapi.dto.request.UpdateCardRequest;
import com.example.ecommarcerestapi.model.Card;

import java.util.List;

public interface CardService {
    void createCard(CreateCardRequest cardRequest);
    Card findById(Long id);
    List<Card> findAll();
    void deleteById(Long id);
    boolean doesExistByCardNumber(String cardNumber);
    void update(UpdateCardRequest cardRequest,Long id);
}
