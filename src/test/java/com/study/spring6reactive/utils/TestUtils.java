package com.study.spring6reactive.utils;

import com.study.spring6reactive.entities.Beer;
import com.study.spring6reactive.entities.Customer;

import java.math.BigDecimal;

public class TestUtils {

    private TestUtils() {
    }

    public static Beer getTestBeer() {
        return Beer.builder()
                .beerName("Space Dust")
                .beerStyle("IPA")
                .price(new BigDecimal("10.99"))
                .quantityOnHand(122)
                .upc("123321")
                .build();
    }

    public static Customer getTestCustomer() {
        return Customer.builder()
                .customerName("Taras Shevchenko")
                .email("kobzar@gmail.com")
                .build();
    }
}
