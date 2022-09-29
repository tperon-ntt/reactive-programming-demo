package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CustomerData {

  private String _id;
  private Long version;
  private String name;
  private String address;
  private String email;

}
