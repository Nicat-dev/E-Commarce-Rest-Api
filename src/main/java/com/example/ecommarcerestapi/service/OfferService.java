package com.example.ecommarcerestapi.service;

import com.example.ecommarcerestapi.dto.request.CreateOfferRequest;
import com.example.ecommarcerestapi.dto.request.UpdateOfferRequest;
import com.example.ecommarcerestapi.model.Offer;


import java.util.List;

public interface OfferService {
    void create(CreateOfferRequest offerRequest);
    Offer findById(Long id);
    List<Offer> findAll();
    void deleteById(Long id);
    boolean doesExistById(Long id);
    void update(UpdateOfferRequest offerRequest,Long id);
}
