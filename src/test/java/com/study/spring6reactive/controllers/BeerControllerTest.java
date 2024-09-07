package com.study.spring6reactive.controllers;

import com.study.spring6reactive.model.BeerDTO;
import com.study.spring6reactive.utils.TestUtils;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static com.study.spring6reactive.controllers.BeerController.BEER_ID_PATH;
import static com.study.spring6reactive.controllers.BeerController.BEER_PATH;

@SpringBootTest
@AutoConfigureWebTestClient
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BeerControllerTest {

    @Autowired
    WebTestClient webClient;

    @Test
    @Order(1)
    void testGetAllBeers() {
        webClient.get().uri(BEER_PATH)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody().jsonPath("$.size()").isEqualTo(3);
    }

    @Test
    @Order(2)
    void testGetBeerById() {
        webClient.get().uri(BEER_ID_PATH, 1)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody().jsonPath("$.id").isEqualTo(1);
    }

    @Test
    @Order(3)
    void testCreateBeer() {
        webClient.post().uri(BEER_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(TestUtils.getTestBeer()), BeerDTO.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().location("http://localhost:8080/api/v2/beers/4");
    }

    @Test
    @Order(4)
    void testUpdateBeer() {
        var testBeer = TestUtils.getTestBeer();
        String updatedName = "updated";
        testBeer.setBeerName(updatedName);

        webClient.put().uri(BEER_ID_PATH, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(testBeer), BeerDTO.class)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    @Order(999)
    void testDeleteBeer() {
        webClient.delete().uri(BEER_ID_PATH, 1)
                .exchange()
                .expectStatus().isNoContent();
    }

}