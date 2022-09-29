package com.example.demo.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
@ConfigurationProperties(prefix = "customer-client")
@Getter
@Data
public class CustomerServiceClientConfig extends WebClientConfig{

  private String saveEndpoint;
  private String getEndpoint;

  @Bean(name = "customerClient")
  public WebClient customerClient(WebClient.Builder builder) {
    return super.getWebClient(builder);
  }
}
