package com.study.spring6reactive.controllers;

import com.study.spring6reactive.model.CustomerDTO;
import com.study.spring6reactive.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    public static final String ROOT_URL = "http://localhost:8080";
    public static final String CUSTOMER_PATH = "/api/v2/customers";
    public static final String CUSTOMER_ID_PATH = CUSTOMER_PATH + "/{id}";

    private final CustomerService customerService;

    @GetMapping(CUSTOMER_PATH)
    public Flux<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping(CUSTOMER_ID_PATH)
    public Mono<CustomerDTO> getCustomerById(@PathVariable("id") Integer id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping(CUSTOMER_PATH)
    public Mono<ResponseEntity<Void>> createCustomer(@Validated @RequestBody CustomerDTO customerDTO) {
        return customerService.saveNewCustomer(customerDTO)
                .map(savedDto ->
                        ResponseEntity
                                .created(UriComponentsBuilder
                                        .fromHttpUrl(ROOT_URL + CUSTOMER_PATH + "/" + savedDto.getId())
                                        .build().toUri())
                                .build()
                );
    }

    @PutMapping(CUSTOMER_ID_PATH)
    public Mono<ResponseEntity<Void>> updateCustomer(@PathVariable("id") Integer id,
                                                 @Validated @RequestBody CustomerDTO customerDTO) {
        return customerService
                .updateCustomer(id, customerDTO)
                .map(updatedDto -> ResponseEntity.noContent().build());
    }

    @PatchMapping(CUSTOMER_ID_PATH)
    public Mono<ResponseEntity<Void>> patchCustomer(@PathVariable("id") Integer id,
                                                @Validated @RequestBody CustomerDTO customerDTO) {
        return customerService
                .patchCustomer(id, customerDTO)
                .map(patchedDto -> ResponseEntity.noContent().build());
    }

    @DeleteMapping(CUSTOMER_ID_PATH)
    public Mono<ResponseEntity<Void>> deleteCustomer(@PathVariable("id") Integer id) {
        return customerService
                .deleteCustomer(id)
                .then(Mono.fromCallable(() -> ResponseEntity.noContent().build()));
    }
}
