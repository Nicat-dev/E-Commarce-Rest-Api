package com.example.ecommarcerestapi.repository;

import com.example.ecommarcerestapi.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Long> {
    Offer findOfferByOfferId(Long id);
    Offer findOfferByOfferName(String offerName);
    List<Offer> findAllByStatus(Integer status);
}
