package com.example.ecommarcerestapi.service.serviceImpl;

import com.example.ecommarcerestapi.dto.request.CreateOfferRequest;
import com.example.ecommarcerestapi.dto.request.UpdateOfferRequest;
import com.example.ecommarcerestapi.enums.EnumAvaibleStatus;
import com.example.ecommarcerestapi.exception.DomainNotFoundException;
import com.example.ecommarcerestapi.exception.MethodNullArgumentException;
import com.example.ecommarcerestapi.mapper.OfferMapper;
import com.example.ecommarcerestapi.model.Offer;
import com.example.ecommarcerestapi.model.OfferType;
import com.example.ecommarcerestapi.repository.OfferRepository;
import com.example.ecommarcerestapi.service.OfferService;
import com.example.ecommarcerestapi.service.OfferTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service(value = "OfferService")
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;
    private final OfferTypeService offerTypeService;

    @Override
    public void create(CreateOfferRequest offerRequest) {
        final long offerTypeId = offerRequest.getOfferTypeId();
        OfferType offerType = offerTypeService.findOfferTypeById(offerTypeId);

        Offer offer = offerMapper.toEntity(offerRequest);
        offer.setOfferType(offerType);
        offerRepository.save(offer);

    }

    @Override
    public Offer findById(Long id) {
        if (id == null)
            throw new MethodNullArgumentException("id can not be null");
        return offerRepository.findById(id).orElseThrow(
                ()-> new DomainNotFoundException("Offer not found")
        );
    }

    @Override
    public List<Offer> findAll() {
        List<Offer> offers = offerRepository.findAllByStatus(EnumAvaibleStatus.ACTIVE.getValue());
        if (offers == null)
            throw new DomainNotFoundException("Offers not found");
        return offers;
    }

    @Override
    public void deleteById(Long id) {
        if (id == null)
            throw new MethodNullArgumentException("id can not be null");
        Offer offer = offerRepository.findOfferByOfferId(id);
        offer.setStatus(EnumAvaibleStatus.DEACTIVE.getValue());
        offerRepository.save(offer);
    }

    @Override
    public boolean doesExistById(Long id) {
        if (id== null)
            throw new MethodNullArgumentException("offer id can not be null");
        return offerRepository.findById(id).isPresent();
    }

    @Override
    public void update(UpdateOfferRequest offerRequest, Long id) {
        if (id== null)
            throw new MethodNullArgumentException("id can not be null");
        Offer offer = offerRepository.findById(id).orElseThrow(
                ()-> new DomainNotFoundException("offer can not found")
        );
        offerRepository.save(updateOffer(offer,offerRequest));
    }
    private Offer updateOffer(Offer offer,UpdateOfferRequest request){
        Offer offer1 = Offer.builder()
                .offerName(request.getOfferName() == null ?
                        offer.getOfferName() : offer.getOfferName())
                .offerType(request.getOfferTypeId() == null ?
                        offer.getOfferType() : offer.getOfferType())
                .build();
        return null;
    }
}
