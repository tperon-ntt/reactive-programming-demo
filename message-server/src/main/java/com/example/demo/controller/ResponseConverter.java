package com.example.demo.controller;

import com.example.demo.model.CustomerData;
import com.example.demo.model.CustomerDataAll;
import com.example.demo.model.CustomerDataResponse;
import com.example.demo.model.MarketingData;
import org.springframework.stereotype.Service;
import reactor.util.function.Tuple2;

@Service
public class ResponseConverter {
  public CustomerDataResponse convert(
      Tuple2<CustomerData, MarketingData> result
  ) {
    CustomerData customerData = result.getT1();
    MarketingData marketingData = result.getT2();

    return CustomerDataResponse.builder()
        .email(customerData.getEmail())
        .created(true)
        .welcomeMail(marketingData.getWelcomeMail())
        .newsMail(marketingData.getNewsMail())
        .build();
  }

  public CustomerDataAll convertAllResponse(
      Tuple2<CustomerData, MarketingData> result
  ) {
    CustomerData customerData = result.getT1();
    MarketingData marketingData = result.getT2();

    return CustomerDataAll.builder()
        .email(customerData.getEmail())
        .name(customerData.getName())
        .address(customerData.getAddress())
        .welcomeMail(marketingData.getWelcomeMail())
        .newsMail(marketingData.getNewsMail())
        .build();
  }
}
