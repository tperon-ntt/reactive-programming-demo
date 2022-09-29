package com.example.demo.controller;

import com.example.demo.model.CustomerDataAll;
import com.example.demo.model.CustomerDataRequest;
import com.example.demo.model.CustomerDataResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/message/new")
@Slf4j
public class MessageBroadcastController {

  @Autowired
  private MessageFacade messageFacade;

  @PostMapping("/customer")
  public Mono<CustomerDataResponse> saveCustomer(
      @RequestBody final CustomerDataRequest request
  ) {
    log.info("New customer data");
    return messageFacade.saveCustomer(request);
  }

  @GetMapping("/all")
  public Flux<CustomerDataAll> getAllCustomers( ) {
    return messageFacade.getAllCustomers();
  }
}
