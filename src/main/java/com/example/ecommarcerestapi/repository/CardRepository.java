package com.example.ecommarcerestapi.repository;

import com.example.ecommarcerestapi.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card,Long> {
    Card findCardByCardIdAndStatus(Long id, Integer status);
    List<Card> findAllByStatus(Integer status);
}
