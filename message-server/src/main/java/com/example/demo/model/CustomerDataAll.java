package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDataAll {

  private String email;
  private String name;
  private String address;
  private Boolean newsMail;
  private Boolean welcomeMail;

}
