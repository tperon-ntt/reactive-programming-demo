package com.example.demo.controller;

import com.example.demo.model.CustomerDataAll;
import com.example.demo.model.CustomerDataRequest;
import com.example.demo.model.CustomerDataResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class MessageFacade {

  @Autowired
  private ResponseConverter responseConverter;

  @Autowired
  private CustomerService customerService;

  @Autowired
  private MarketingService marketingService;


  public Mono<CustomerDataResponse> saveCustomer(CustomerDataRequest customerDataRequest) {
    return Mono.zip(
              customerService.callSaveCustomer(customerDataRequest),
              marketingService.callSaveMarketing(customerDataRequest)
          ).map( result -> responseConverter.convert(result) );
  }


  public Flux<CustomerDataAll> getAllCustomers( ) {
    return Flux.zip(
        customerService.getAll(),
        marketingService.getAll()
    ).map(
        result -> responseConverter.convertAllResponse(result)
    );
  }

}
