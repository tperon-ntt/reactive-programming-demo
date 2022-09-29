package com.example.demo.controller;

import com.example.demo.config.MarketingServiceClientConfig;
import com.example.demo.model.CustomerDataRequest;
import com.example.demo.model.MarketingData;
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
public class MarketingService {

  @Autowired
  private WebClient marketingClient;

  @Autowired
  private MarketingServiceClientConfig marketingServiceClientConfig;

  public Mono<MarketingData> callSaveMarketing(
      CustomerDataRequest request
  ) {
    log.info("Save Marketing data");
    return marketingClient.post()
        .uri((uriBuilder -> uriBuilder.path(marketingServiceClientConfig.getSaveEndpoint()).build()))
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(request)
        .retrieve()
        .bodyToMono(MarketingData.class)
        .timeout(Duration.ofSeconds(marketingServiceClientConfig.getClientTimeout()))
        .retry();
  }

  public Flux<MarketingData> getAll() {
    log.info("Get All Marketing data");
    return marketingClient.get()
        .uri((uriBuilder -> uriBuilder.path(marketingServiceClientConfig.getGetEndpoint()).build()))
        .retrieve()
        .bodyToFlux(MarketingData.class)
        .timeout(Duration.ofSeconds(marketingServiceClientConfig.getClientTimeout()))
        .retry();
  }
}
