package com.study.spring6reactive.repositories;

import com.study.spring6reactive.config.DatabaseConfig;
import com.study.spring6reactive.entities.Beer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

@DataR2dbcTest
@Import(DatabaseConfig.class)
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testSaveNewBeer() {
        StepVerifier.create(beerRepository
                        .save(getTestBeer()))
                .expectNextMatches(beer -> beer.getId() != null)
                .verifyComplete();
    }

    Beer getTestBeer() {
        return Beer.builder()
                .beerName("Space Dust")
                .beerStyle("IPA")
                .price(new BigDecimal("10.99"))
                .quantityOnHand(122)
                .upc("123321")
                .build();
    }

}