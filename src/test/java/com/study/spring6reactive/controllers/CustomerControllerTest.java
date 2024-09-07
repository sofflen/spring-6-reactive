package com.study.spring6reactive.controllers;

import com.study.spring6reactive.model.CustomerDTO;
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

import static com.study.spring6reactive.controllers.CustomerController.CUSTOMER_ID_PATH;
import static com.study.spring6reactive.controllers.CustomerController.CUSTOMER_PATH;

@SpringBootTest
@AutoConfigureWebTestClient
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerControllerTest {

    @Autowired
    WebTestClient webClient;

    @Test
    @Order(1)
    void testGetAllCustomers() {
        webClient.get().uri(CUSTOMER_PATH)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody().jsonPath("$.size()").isEqualTo(3);
    }

    @Test
    @Order(2)
    void testGetCustomerById() {
        webClient.get().uri(CUSTOMER_ID_PATH, 1)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody().jsonPath("$.id").isEqualTo(1);
    }

    @Test
    @Order(3)
    void testCreateCustomer() {
        webClient.post().uri(CUSTOMER_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(TestUtils.getTestCustomer()), CustomerDTO.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().location("http://localhost:8080/api/v2/customers/4");
    }

    @Test
    @Order(4)
    void testUpdateCustomer() {
        var testCustomer = TestUtils.getTestCustomer();
        String updatedName = "updated";
        testCustomer.setCustomerName(updatedName);

        webClient.put().uri(CUSTOMER_ID_PATH, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(testCustomer), CustomerDTO.class)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    @Order(999)
    void testDeleteCustomer() {
        webClient.delete().uri(CUSTOMER_ID_PATH, 1)
                .exchange()
                .expectStatus().isNoContent();
    }

}
