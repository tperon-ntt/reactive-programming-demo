package com.example.demo.controller;

import com.example.demo.config.CustomerServiceClientConfig;
import com.example.demo.model.CustomerData;
import com.example.demo.model.CustomerDataRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@Slf4j
public class CustomerService {

  @Autowired
  private WebClient customerClient;

  @Autowired
  private CustomerServiceClientConfig customerServiceClientConfig;

  public Mono<CustomerData> callSaveCustomer(
      CustomerDataRequest request
  ) {
    log.info("Call save customer from {}", request);
    return customerClient.post()
        .uri((uriBuilder -> uriBuilder.path(customerServiceClientConfig.getSaveEndpoint()).build()))
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(request)
        .retrieve()
        .bodyToMono(CustomerData.class)
        .timeout(Duration.ofSeconds(customerServiceClientConfig.getClientTimeout()))
        .retry(3);
  }

  public Flux<CustomerData> getAll() {
    log.info("Get all customers");
    return customerClient.get()
        .uri((uriBuilder -> uriBuilder.path(customerServiceClientConfig.getGetEndpoint()).build()))
        .retrieve()
        .bodyToFlux(CustomerData.class)
        .timeout(Duration.ofSeconds(customerServiceClientConfig.getClientTimeout()))
        .retry(3);
  }
}
