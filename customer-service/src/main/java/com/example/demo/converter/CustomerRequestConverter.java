package com.example.demo.converter;

import com.example.demo.model.CustomerData;
import com.example.demo.model.CustomerDataRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CustomerRequestConverter {

  public CustomerData convert(CustomerDataRequest customerDataRequest) {

    CustomerData customer = CustomerData.builder().build();
    BeanUtils.copyProperties(customerDataRequest, customer);

    return customer;
  }

}
