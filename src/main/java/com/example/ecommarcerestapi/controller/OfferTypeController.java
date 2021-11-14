package com.example.ecommarcerestapi.controller;

import com.example.ecommarcerestapi.dto.request.CreateOfferTypeRequest;
import com.example.ecommarcerestapi.dto.request.UpdateOfferTypeRequest;
import com.example.ecommarcerestapi.dto.response.ApiMessage;
import com.example.ecommarcerestapi.dto.response.OfferTypeResponse;
import com.example.ecommarcerestapi.mapper.OfferTypeMapper;
import com.example.ecommarcerestapi.service.OfferTypeService;
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
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/offerType")
public class OfferTypeController implements ApiBuilder {
    private final OfferTypeService offerTypeService;
    private final OfferTypeMapper offerTypeMapper;

    @PostMapping
    public ResponseEntity<ApiMessage> create(@Valid @RequestBody CreateOfferTypeRequest request){
        log.info("Service for creation accepted");

        offerTypeService.create(request);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(generateAccepted());
    }

    @GetMapping
    public ResponseEntity<List<OfferTypeResponse>> findAll(){
        List<OfferTypeResponse> offerTypeResponses = offerTypeService.findAll().stream()
                .map(offerTypeMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(offerTypeResponses);
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiMessage> update(@Valid @RequestBody UpdateOfferTypeRequest request,@PathVariable Long id){
        offerTypeService.update(request, id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(generateAccepted());
    }

    @DeleteMapping("{id}")
    public  ResponseEntity<ApiMessage> delete(@PathVariable Long id){
        offerTypeService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(generateAccepted());
    }


}
