package com.training.springlombok.service;

import com.training.springlombok.model.BeerDTO;
import com.training.springlombok.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

    private Map<UUID, BeerDTO> beerMap = new HashMap<>();
    public BeerServiceImpl() {
        BeerDTO beer1 = BeerDTO.builder()
                .id(UUID.randomUUID())
                .beerName("KFC")
                .beerStyle(BeerStyle.LAGER)
                .upc("123456")
                .quantityInHand(122)
                .price(new BigDecimal(90.50))
                .createdDateTime(LocalDateTime.now())
                .lastModifiedDateTime(LocalDateTime.now())
                .build();
        BeerDTO beer2 = BeerDTO.builder()
                .id(UUID.randomUUID())
                .beerName("Dominos")
                .beerStyle(BeerStyle.ALE)
                .upc("0987")
                .quantityInHand(203)
                .price(new BigDecimal(290.50))
                .createdDateTime(LocalDateTime.now())
                .lastModifiedDateTime(LocalDateTime.now())
                .build();
        BeerDTO beer3 = BeerDTO.builder()
                .id(UUID.randomUUID())
                .beerName("Sangeethas")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("675")
                .quantityInHand(987)
                .price(new BigDecimal(178.50))
                .createdDateTime(LocalDateTime.now())
                .lastModifiedDateTime(LocalDateTime.now())
                .build();
        beerMap.put(beer1.getId(), beer1);
        beerMap.put(beer2.getId(), beer2);
        beerMap.put(beer3.getId(), beer3);
    }


    @Override
    public List<BeerDTO> listBeers() {
        log.info("In BeerServiceImpl: getBeerById");
        return new ArrayList<>(beerMap.values());
    }

    @Override
    public Optional<BeerDTO> getBeerById(UUID id) {
        log.info("In BeerServiceImpl: getBeerById");
        return Optional.ofNullable(beerMap.get(id));
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beer) {
        BeerDTO savedBeer = BeerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .createdDateTime(LocalDateTime.now())
                .lastModifiedDateTime(LocalDateTime.now())
                .beerName(beer.getBeerName())
                .beerStyle(beer.getBeerStyle())
                .upc(beer.getUpc())
                .quantityInHand(beer.getQuantityInHand())
                .price(beer.getPrice())
                .build();
        beerMap.put(savedBeer.getId(), savedBeer);
        return savedBeer;
    }

    @Override
    public Optional<BeerDTO> updateBeer(UUID beerId, BeerDTO beer) {
        BeerDTO existing = beerMap.get(beerId);
        existing.setBeerName(beer.getBeerName());
        existing.setBeerStyle(beer.getBeerStyle());
        existing.setPrice(beer.getPrice());
        existing.setUpc(beer.getUpc());
        existing.setQuantityInHand(beer.getQuantityInHand());

        beerMap.put(existing.getId(), existing);
        return Optional.of(existing);
    }

    @Override
    public Boolean deleteByID(UUID beerId) {
        beerMap.remove(beerId);
        return true;
    }

    @Override
    public void updatePatchBeer(UUID beerId, BeerDTO beer) {
        BeerDTO existing = beerMap.get(beerId);

        if(StringUtils.hasText(beer.getBeerName())) {
            existing.setBeerName(beer.getBeerName());
        }
        if(Objects.nonNull(beer.getPrice())) {
            existing.setPrice(beer.getPrice());
        }
        if(Objects.nonNull(beer.getQuantityInHand())) {
            existing.setQuantityInHand(beer.getQuantityInHand());
        }
        if(StringUtils.hasText(beer.getUpc())) {
            existing.setUpc(beer.getUpc());
        }
        if(StringUtils.hasText(String.valueOf(beer.getBeerStyle()))) {
            existing.setBeerStyle(beer.getBeerStyle());
        }
    }
}
