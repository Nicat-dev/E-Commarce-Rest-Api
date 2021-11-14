package com.example.ecommarcerestapi.repository;

import com.example.ecommarcerestapi.model.OfferType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferTypeRepository extends JpaRepository<OfferType,Long> {
    OfferType findByOfferTypeIdAndStatus(Long id,Integer status);
    OfferType findOfferTypeByOfferTypeNameAndStatus(String name,Integer status);
}
