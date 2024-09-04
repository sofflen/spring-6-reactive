package com.study.spring6reactive.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BeerDTO {
    private Integer id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @NotBlank
    @Size(min = 3, max = 255)
    private String beerName;
    @Size(min = 3, max = 255)
    private String beerStyle;
    @Size(max = 25)
    private String upc;
    private Integer quantityOnHand;
    private BigDecimal price;
}