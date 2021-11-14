package com.example.ecommarcerestapi.service.serviceImpl;

import com.example.ecommarcerestapi.dto.request.CreateCardRequest;
import com.example.ecommarcerestapi.dto.request.UpdateCardRequest;
import com.example.ecommarcerestapi.enums.EnumAvaibleStatus;
import com.example.ecommarcerestapi.exception.DomainNotFoundException;
import com.example.ecommarcerestapi.exception.MethodNullArgumentException;
import com.example.ecommarcerestapi.mapper.CardMapper;
import com.example.ecommarcerestapi.model.Card;
import com.example.ecommarcerestapi.repository.CardRepository;
import com.example.ecommarcerestapi.service.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service(value = "CardService")
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardMapper cardMapper;
    private final CardRepository cardRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createCard(CreateCardRequest cardRequest) {
        log.info("Card saccessfully craeted");
        cardRequest.setCardNumber(passwordEncoder.encode(cardRequest.getCardNumber()));
        Card card = cardMapper.toEntity(cardRequest);
        cardRepository.save(card);
    }

    @Override
    public Card findById(Long id) {
        if (id == null)
            throw new MethodNullArgumentException("Id can not be null");
        return cardRepository.findById(id).orElseThrow(
                ()-> new DomainNotFoundException("Domain not found"));
    }

    @Override
    public List<Card> findAll() {
        List<Card> cardList = cardRepository.findAllByStatus(EnumAvaibleStatus.ACTIVE.getValue());
        if (cardList == null)
            throw new DomainNotFoundException("Domain not found");
        return cardList;
    }

    @Override
    public void deleteById(Long id) {
        if (id == null)
            throw new MethodNullArgumentException("id can not be null");
        Card card = cardRepository.findCardByCardIdAndStatus(id,EnumAvaibleStatus.ACTIVE.getValue());
        if (card == null)
            throw new DomainNotFoundException("this account not found");
        card.setStatus(EnumAvaibleStatus.DEACTIVE.getValue());
        cardRepository.save(card);
    }

    @Override
    public boolean doesExistByCardNumber(String cardNumber) {
        return false;
    }

    @Override
    @Transactional
    public void update(UpdateCardRequest cardRequest, Long id) {
        if (id == null)
            throw new MethodNullArgumentException("id can not be null");
        Card card = cardRepository.findById(id).orElseThrow(
                ()-> new DomainNotFoundException("card infomrmation not found")
        );
        cardRepository.save(updateCard(card,cardRequest));
    }

    private Card updateCard(Card card,UpdateCardRequest request){

        Card card1 = Card.builder()
                .cardNumber(request.getCardNumber() == null?
                        card.getCardNumber() : card.getCardNumber())
                .cardPin(request.getCardPin() == null?
                        card.getCardPin() : card.getCardPin())
                .cardCode(request.getCardCode() == null?
                        card.getCardCode() : card.getCardCode())
                .build();

        return card1;
    }
}
