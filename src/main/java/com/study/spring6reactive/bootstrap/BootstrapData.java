package com.study.spring6reactive.bootstrap;

import com.study.spring6reactive.entities.Beer;
import com.study.spring6reactive.entities.Customer;
import com.study.spring6reactive.repositories.BeerRepository;
import com.study.spring6reactive.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final BeerRepository beerRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        loadBeerData();
        loadCustomerData();
    }

    private void loadBeerData() {
        beerRepository.count().subscribe(count -> {
            if (count == 0) {
                Beer beer1 = Beer.builder()
                        .beerName("Galaxy Cat")
                        .beerStyle("Pale Ale")
                        .upc("12356")
                        .price(new BigDecimal("12.99"))
                        .quantityOnHand(122)
                        .build();
                Beer beer2 = Beer.builder()
                        .beerName("Crank")
                        .beerStyle("Pale Ale")
                        .upc("12356222")
                        .price(new BigDecimal("11.99"))
                        .quantityOnHand(392)
                        .build();
                Beer beer3 = Beer.builder()
                        .beerName("Sunshine City")
                        .beerStyle("IPA")
                        .upc("12356")
                        .price(new BigDecimal("13.99"))
                        .quantityOnHand(144)
                        .build();

                beerRepository.saveAll(List.of(beer1, beer2, beer3)).subscribe();
            }
        });

        beerRepository.count().subscribe(count -> System.out.println("Found " + count + " beers"));
    }

    private void loadCustomerData() {
        customerRepository.count().subscribe(count -> {
            if (count == 0) {
                Customer customer1 = Customer.builder()
                        .customerName("John Doe")
                        .email("john.doe@gmail.com")
                        .build();
                Customer customer2 = Customer.builder()
                        .customerName("Jane Doe")
                        .email("jane.doe@gmail.com")
                        .build();
                Customer customer3 = Customer.builder()
                        .customerName("Thomas Doe")
                        .email("thomas.doe@gmail.com")
                        .build();

                customerRepository.saveAll(List.of(customer1, customer2, customer3)).subscribe();
            }
        });

        customerRepository.count().subscribe(count -> System.out.println("Found " + count + " customers"));
    }
}
