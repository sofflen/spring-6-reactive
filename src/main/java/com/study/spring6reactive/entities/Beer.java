package com.study.spring6reactive.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Beer {
    @Id
    private Integer id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String beerName;
    private String beerStyle;
    private String upc;
    private Integer quantityOnHand;
    private BigDecimal price;
}
