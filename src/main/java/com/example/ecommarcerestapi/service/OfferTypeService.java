package com.example.ecommarcerestapi.service;

import com.example.ecommarcerestapi.dto.request.CreateOfferTypeRequest;
import com.example.ecommarcerestapi.dto.request.UpdateOfferRequest;
import com.example.ecommarcerestapi.dto.request.UpdateOfferTypeRequest;
import com.example.ecommarcerestapi.model.OfferType;

import java.util.List;

public interface OfferTypeService {
    void create(CreateOfferTypeRequest createOfferTypeRequest);
    OfferType findOfferTypeById(Long id);
    List<OfferType> findAll();
    void delete(Long id);
    void update(UpdateOfferTypeRequest offerRequest, Long id);
}
