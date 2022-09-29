package com.example.demo.controller;

import com.example.demo.converter.CustomerRequestConverter;
import com.example.demo.model.CustomerData;
import com.example.demo.model.CustomerDataRequest;
import com.example.demo.repo.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/customer")
@Slf4j
public class CustomerController {

  @Autowired
  private CustomerRepository customerRepository;
  @Autowired
  private CustomerRequestConverter customerRequestConverter;

  @PostMapping("/save")
  public Mono<CustomerData> saveCustomer(
      @RequestBody final CustomerDataRequest request
  ) {
    log.info("save new customer {}",request);
    return Mono.just(request)
        .map( req -> customerRequestConverter.convert(req))
        .flatMap( customerData -> customerRepository.save(customerData));
  }

  @GetMapping("/get/all")
  public Flux<CustomerData> getAllCustomers() {
    return customerRepository.findAll();
  }
}
