package com.example.demo.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
@ConfigurationProperties(prefix = "marketing-client")
@Data
public class MarketingServiceClientConfig extends WebClientConfig{

  private String saveEndpoint;
  private String getEndpoint;


  @Bean(name = "marketingClient")
  public WebClient marketingClient(WebClient.Builder builder) {
    return super.getWebClient(builder);
  }
}
