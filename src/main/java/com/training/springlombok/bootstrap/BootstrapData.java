package com.training.springlombok.bootstrap;

import com.training.springlombok.entity.Beer;
import com.training.springlombok.entity.Customer;
import com.training.springlombok.model.BeerStyle;
import com.training.springlombok.repository.BeerRepository;
import com.training.springlombok.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final BeerRepository beerRepository;

    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        loadBeerData();
        loadCustomerData();
    }

    private void loadBeerData() {
        Beer beer1 = Beer.builder()
                .id(UUID.randomUUID())
                .beerName("KFC")
                .beerStyle(BeerStyle.LAGER)
                .upc("123456")
                .quantityInHand(122)
                .price(new BigDecimal(90.50))
                .createdDateTime(LocalDateTime.now())
                .lastModifiedDateTime(LocalDateTime.now())
                .build();
        Beer beer2 = Beer.builder()
                .id(UUID.randomUUID())
                .beerName("Dominos")
                .beerStyle(BeerStyle.ALE)
                .upc("0987")
                .quantityInHand(203)
                .price(new BigDecimal(290.50))
                .createdDateTime(LocalDateTime.now())
                .lastModifiedDateTime(LocalDateTime.now())
                .build();
        Beer beer3 = Beer.builder()
                .id(UUID.randomUUID())
                .beerName("Sangeethas")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("675")
                .quantityInHand(987)
                .price(new BigDecimal(178.50))
                .createdDateTime(LocalDateTime.now())
                .lastModifiedDateTime(LocalDateTime.now())
                .build();
        beerRepository.save(beer1);
        beerRepository.save(beer2);
        beerRepository.save(beer3);
    }

    private void loadCustomerData() {
        Customer cust1 = Customer.builder()
                .id(UUID.randomUUID())
                .customerName("Dharshini")
                .createdDateTime(LocalDateTime.now())
                .lastModifiedDateTime(LocalDateTime.now())
                .build();
        Customer cust2 = Customer.builder()
                .id(UUID.randomUUID())
                .customerName("Nandhu")
                .createdDateTime(LocalDateTime.now())
                .lastModifiedDateTime(LocalDateTime.now())
                .build();
        Customer cust3 = Customer.builder()
                .id(UUID.randomUUID())
                .customerName("Srilaya")
                .createdDateTime(LocalDateTime.now())
                .lastModifiedDateTime(LocalDateTime.now())
                .build();
        customerRepository.saveAll(Arrays.asList(cust1, cust2, cust3));
    }
}
