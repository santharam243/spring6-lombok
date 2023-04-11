package com.training.springlombok.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class Beer {
    private UUID id;
    private Integer version;
    private String beerName;
    private BeerStyle beerStyle;
    private String upc;
    private Integer quantityInHand;
    private BigDecimal price;
    private LocalDateTime createdDateTime;
    private LocalDateTime lastModifiedDateTime;
}
