package com.example.ecommarcerestapi.service.serviceImpl;

import com.example.ecommarcerestapi.dto.request.CreateOfferTypeRequest;
import com.example.ecommarcerestapi.dto.request.UpdateOfferRequest;
import com.example.ecommarcerestapi.dto.request.UpdateOfferTypeRequest;
import com.example.ecommarcerestapi.enums.EnumAvaibleStatus;
import com.example.ecommarcerestapi.exception.DomainNotFoundException;
import com.example.ecommarcerestapi.exception.MethodNullArgumentException;
import com.example.ecommarcerestapi.mapper.OfferTypeMapper;
import com.example.ecommarcerestapi.model.OfferType;
import com.example.ecommarcerestapi.repository.OfferTypeRepository;
import com.example.ecommarcerestapi.service.OfferTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service(value = "OfferTypeService")
@RequiredArgsConstructor
public class OfferTypeServiceImpl implements OfferTypeService {
    private final OfferTypeMapper offerTypeMapper;
    private final OfferTypeRepository offerTypeRepository;


    @Override
    @Transactional
    public void create(CreateOfferTypeRequest createOfferTypeRequest) {
        OfferType offerType = offerTypeMapper.toEntity(createOfferTypeRequest);
        offerTypeRepository.save(offerType);
    }

    @Override
    public List<OfferType> findAll() {
        List<OfferType> offerTypes = offerTypeRepository.findAll();
        if (offerTypes.isEmpty())
            throw new DomainNotFoundException("domain not found");
        return offerTypes;
    }

    @Override
    public OfferType findOfferTypeById(Long id) {
        if (id== null)
            throw new MethodNullArgumentException("id can not be null");
        return offerTypeRepository.findById(id).orElseThrow(
                ()-> new DomainNotFoundException("Domain not found"));
    }

    @Override
    public void delete(Long id) {
        if (id == null)
            throw new MethodNullArgumentException("id can not be empty");
        if (!offerTypeRepository.existsById(id))
            throw new DomainNotFoundException("id not found exception");
         OfferType offerType = offerTypeRepository.findByOfferTypeIdAndStatus(id, EnumAvaibleStatus.ACTIVE.getValue());
         offerType.setStatus(EnumAvaibleStatus.DEACTIVE.getValue());
         offerTypeRepository.save(offerType);
    }

    @Override
    @Transactional
    public void update(UpdateOfferTypeRequest offerRequest, Long id) {
        if (id == null)
            throw new MethodNullArgumentException("id can not be null");
        OfferType offerType = offerTypeRepository.findById(id).orElseThrow(
                ()-> new DomainNotFoundException("Domain not found exception"));
        offerTypeRepository.save(updateOfferType(offerType,offerRequest));

    }

    private OfferType updateOfferType(OfferType offerType, UpdateOfferTypeRequest request){

        OfferType offerType1 = OfferType.builder()
                .offerTypeId(offerType.getOfferTypeId())
                .offerTypeName(request.getOfferTypeName() == null ?
                        offerType.getOfferTypeName() : offerType.getOfferTypeName())
                .offerSize(request.getOfferSize() == null ?
                        offerType.getOfferSize() : offerType.getOfferSize())
                .offerCpu(request.getOfferCpu() == null ?
                        offerType.getOfferCpu() : offerType.getOfferCpu())
                .rem(request.getRem() == null ?
                        offerType.getRem() : offerType.getRem())
                .videoCard(request.getVideoCard() == null ?
                        offerType.getVideoCard() : offerType.getVideoCard())
                .ram(request.getRam() == null ?
                        offerType.getRam() : offerType.getRam())
                .offerBatteryLife(request.getOfferBatteryLife() == null ?
                        offerType.getOfferBatteryLife() : offerType.getOfferBatteryLife())
                .build();

        return offerType1;
    }

}
