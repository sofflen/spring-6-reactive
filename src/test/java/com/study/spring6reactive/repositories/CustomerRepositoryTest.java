package com.study.spring6reactive.repositories;

import com.study.spring6reactive.config.DatabaseConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;
import reactor.test.StepVerifier;

import static com.study.spring6reactive.utils.TestUtils.getTestCustomer;

@DataR2dbcTest
@Import(DatabaseConfig.class)
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void testSaveNewCustomer() {
        StepVerifier.create(customerRepository
                        .save(getTestCustomer()))
                .expectNextMatches(customer -> customer.getId() != null)
                .verifyComplete();
    }
}
