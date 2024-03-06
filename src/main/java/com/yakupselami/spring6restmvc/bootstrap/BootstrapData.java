package com.yakupselami.spring6restmvc.bootstrap;

import com.yakupselami.spring6restmvc.entities.Beer;
import com.yakupselami.spring6restmvc.entities.Customer;
import com.yakupselami.spring6restmvc.model.BeerStyle;
import com.yakupselami.spring6restmvc.repositories.BeerRepository;
import com.yakupselami.spring6restmvc.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner{

    private final BeerRepository beerRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        loaderBeerData();
        loaderCustomerData();
    }

    private void loaderBeerData(){
        if (beerRepository.count() == 0) {
            Beer beer1 = Beer.builder()
                    .beerName("Galaxy Cat")
                    .beerStyle(BeerStyle.PALE_ALE)
                    .upc("12356")
                    .price(new BigDecimal("12.99"))
                    .quantityOnHand(122)
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .build();
            Beer beer2 = Beer.builder()
                    .beerName("Crank")
                    .beerStyle(BeerStyle.PALE_ALE)
                    .upc("12356222")
                    .price(new BigDecimal("11.99"))
                    .quantityOnHand(392)
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .build();

            Beer beer3 = Beer.builder()
                    .beerName("Sunshine City")
                    .beerStyle(BeerStyle.IPA)
                    .upc("12356")
                    .price(new BigDecimal("13.99"))
                    .quantityOnHand(144)
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .build();
            Beer beer4 = Beer.builder()
                    .beerName("Luscious Hoppy Harvest Saison Brewing")
                    .beerStyle(BeerStyle.SAISON)
                    .upc("2325252")
                    .price(new BigDecimal("23.99"))
                    .quantityOnHand(1233)
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .build();

            Beer beer5 = Beer.builder()
                    .beerName("Fuzzy Blossom Botanical Lager")
                    .beerStyle(BeerStyle.LAGER)
                    .upc("21321321")
                    .price(new BigDecimal("19.99"))
                    .quantityOnHand(323)
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .build();

            beerRepository.save(beer1);
            beerRepository.save(beer2);
            beerRepository.save(beer3);
            beerRepository.save(beer4);
            beerRepository.save(beer5);
        }
    }
    private void loaderCustomerData() {
//        if (customerRepository.count() == 0) {
//            Customer customer1 = Customer.builder()
//                    .customerId((UUID.randomUUID()))
//                    .customerName("Alejandra Hudson")
//                    .version(1)
//                    .createdDate(LocalDateTime.now())
//                    .lastModifiedDate(LocalDateTime.now())
//                    .build();
//            Customer customer2 = Customer.builder()
//                    .customerId((UUID.randomUUID()))
//                    .customerName("Aracely Knight")
//                    .version(1)
//                    .createdDate(LocalDateTime.now())
//                    .lastModifiedDate(LocalDateTime.now())
//                    .build();
//            Customer customer3 = Customer.builder()
//                    .customerId((UUID.randomUUID()))
//                    .customerName("Jeremiah Novak")
//                    .version(1)
//                    .createdDate(LocalDateTime.now())
//                    .lastModifiedDate(LocalDateTime.now())
//                    .build();
//            Customer customer4 = Customer.builder()
//                    .customerId((UUID.randomUUID()))
//                    .customerName("Jayden Travis")
//                    .version(1)
//                    .createdDate(LocalDateTime.now())
//                    .lastModifiedDate(LocalDateTime.now())
//                    .build();
//            Customer customer5 = Customer.builder()
//                    .customerId((UUID.randomUUID()))
//                    .customerName("Jovan Maynard")
//                    .version(1)
//                    .createdDate(LocalDateTime.now())
//                    .lastModifiedDate(LocalDateTime.now())
//                    .build();


            //customerRepository.saveAll(Arrays.asList(customer1, customer2, customer3, customer4, customer5));
        }
    }

