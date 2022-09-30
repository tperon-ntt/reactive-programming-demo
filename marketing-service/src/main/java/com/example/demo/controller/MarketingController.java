package com.example.demo.controller;

import com.example.demo.model.CustomerDataRequest;
import com.example.demo.model.MarketingData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/marketing")
@Slf4j
public class MarketingController
{

  @Autowired
  private MarketingService marketingService;

  @PostMapping("/save")
  public Mono<MarketingData> saveCustomer(
      @RequestBody final CustomerDataRequest request
  ) {
    return Mono.just(request.getEmail())
        .flatMap( email ->
            marketingService.sendWelcomeMail(email)
                .flatMap( result ->  marketingService.sendNewsMail(email) )
                .flatMap( result -> marketingService.getMarketingData(email) )
        ).onErrorResume(this::manageError);
  }

  @GetMapping("/get/all")
  public Flux<MarketingData> getAllCustomers() {
    return marketingService.getAllMarketingData();
  }

  @GetMapping("/get/{email}")
  public Mono<MarketingData> getAllCustomers(
      @PathVariable(name = "email") String email
  ) {
    return marketingService.getMarketingData(email);
  }

  private Mono<MarketingData> manageError(Throwable error) {
    log.error( "Error during marketing save ", error);
    return Mono.just(
        MarketingData.builder().build()
    );
  }

}
