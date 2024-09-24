package com.study.spring6reactive.repositories;

import com.study.spring6reactive.bootstrap.BootstrapData;
import com.study.spring6reactive.config.DatabaseConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;
import reactor.test.StepVerifier;

@DataR2dbcTest
@Import({DatabaseConfig.class, BootstrapData.class})
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testGetAllBeers() {
        StepVerifier.create(beerRepository
                        .findAll())
                .expectNextCount(3)
                .verifyComplete();
    }
}
