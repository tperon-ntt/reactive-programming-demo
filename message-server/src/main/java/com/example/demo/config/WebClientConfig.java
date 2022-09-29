package com.example.demo.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;


@Getter
@Setter
@RequiredArgsConstructor
@Slf4j
public abstract class WebClientConfig {

  protected String baseUrl;
  protected long clientTimeout;

  protected WebClient getWebClient(WebClient.Builder builder) {

    WebClient.Builder innerBuilder = builder.baseUrl(this.baseUrl);
    return innerBuilder.build();

  }
}
