package com.example.ecommarcerestapi.controller;

import com.example.ecommarcerestapi.dto.request.CreateOfferRequest;
import com.example.ecommarcerestapi.dto.request.UpdateOfferRequest;
import com.example.ecommarcerestapi.dto.response.ApiMessage;
import com.example.ecommarcerestapi.dto.response.OfferResponse;
import com.example.ecommarcerestapi.mapper.OfferMapper;
import com.example.ecommarcerestapi.mapper.OfferTypeMapper;
import com.example.ecommarcerestapi.service.OfferService;
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
public class OfferController implements ApiBuilder {
        private final OfferService offerService;
        private final OfferMapper offerMapper;
        private final OfferTypeMapper offerTypeMapper;

        @PostMapping
        public ResponseEntity<ApiMessage> create(@Valid @RequestBody CreateOfferRequest request){
            log.info("Offer succesfully created");
            offerService.create(request);

            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(generateAccepted());
        }
        @PostMapping
        public ResponseEntity<List<OfferResponse>> findAll(){
            List<OfferResponse> offerResponses = offerService.findAll().stream()
                    .map(offerMapper::toDto)
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(offerResponses);
        }

        @PutMapping("{id}")
        public ResponseEntity<ApiMessage> update(@Valid @RequestBody UpdateOfferRequest request, @PathVariable Long id){
            offerService.update(request, id);
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(generateAccepted());
        }

        @DeleteMapping("{id}")
        public  ResponseEntity<ApiMessage> delete(@PathVariable Long id){
            log.info("succesfully deleted");
            offerService.deleteById(id);

            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(generateAccepted());
        }


}
