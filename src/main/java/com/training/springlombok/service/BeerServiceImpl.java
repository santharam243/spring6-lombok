package com.training.springlombok.service;

import com.training.springlombok.model.Beer;
import com.training.springlombok.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class BeerServiceImpl implements BeerService {
    @Override
    public Beer getBeerById(UUID id) {
        log.info("In BeerServiceImpl");
        return Beer.builder()
                .id(id)
                .version(1)
                .beerName("KFC")
                .beerStyle(BeerStyle.LAGER)
                .upc("123456")
                .quantityInHand(122)
                .price(new BigDecimal(90.50))
                .createdDateTime(LocalDateTime.now())
                .lastModifiedDateTime(LocalDateTime.now())
                .build();
    }
}
