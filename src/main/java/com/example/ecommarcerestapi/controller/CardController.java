package com.example.ecommarcerestapi.controller;

import com.example.ecommarcerestapi.dto.request.CreateCardRequest;
import com.example.ecommarcerestapi.dto.request.UpdateCardRequest;
import com.example.ecommarcerestapi.dto.response.ApiMessage;
import com.example.ecommarcerestapi.dto.response.CradResponse;
import com.example.ecommarcerestapi.mapper.CardMapper;
import com.example.ecommarcerestapi.service.CardService;
import com.example.ecommarcerestapi.util.ApiBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/cardcontroller")
public class CardController implements ApiBuilder{
    private final CardService cardService;
    private final CardMapper cardMapper;

    @PostMapping
    public ResponseEntity<ApiMessage> create(@Valid @RequestBody CreateCardRequest createCardRequest){
        log.info("Card successfuly created");
        cardService.createCard(createCardRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(generateAccepted());
    }

    @PostMapping
    public ResponseEntity<List<CradResponse>> findAll(){
        List<CradResponse> cardResponse = cardService.findAll().stream()
                .map(cardMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(cardResponse);
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiMessage> update(@Valid @RequestBody UpdateCardRequest request, @PathVariable Long id){
        cardService.update(request, id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(generateAccepted());
    }
    @DeleteMapping("{id}")
    public ResponseEntity<ApiMessage> delete(@PathVariable Long id){
        cardService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(generateAccepted());
    }




}
