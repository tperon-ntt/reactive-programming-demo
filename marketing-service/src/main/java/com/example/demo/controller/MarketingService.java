package com.example.demo.controller;

import com.example.demo.model.MarketingData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class MarketingService {

  @Autowired
  private ReactiveRedisTemplate<String, MarketingData> reactiveRedisTemplate;

  public Mono<Boolean> sendWelcomeMail(String email) {
    log.info(">> send welcome mail to {}", email);
    return reactiveRedisTemplate.opsForValue().get(email)
        .map(this::setWelcomeMail)
        .flatMap( marketingData -> saveMarketingData(email, marketingData))
        .switchIfEmpty(
            reactiveRedisTemplate.opsForValue().set(
                email,
                MarketingData.builder().welcomeMail(true).build()
            )
        );
  }

  public Mono<Boolean> sendNewsMail(String email) {
    log.info(">> send News mail to {}", email);
    return reactiveRedisTemplate.opsForValue().get(email)
        .map(this::setNewsMail)
        .flatMap( marketing -> saveMarketingData(email, marketing) )
        .switchIfEmpty(
            reactiveRedisTemplate.opsForValue().set(
                email,
                MarketingData.builder().newsMail(true).build()
            )
        );
  }

  public Flux<MarketingData> getAllMarketingData() {
    return reactiveRedisTemplate.keys("*@*").flatMap(
        key -> reactiveRedisTemplate.opsForValue().get(key)
    );
  }

  public Flux<Long> deleteAll() {
    return reactiveRedisTemplate.keys("*@*").flatMap(
        key -> reactiveRedisTemplate.delete(key)
    );
  }

  public Mono<MarketingData> getMarketingData(String email) {
    return reactiveRedisTemplate.opsForValue().get(email);
  }

  private Mono<Boolean> saveMarketingData(
      String email,
      MarketingData marketingData
  ) {
    return reactiveRedisTemplate.opsForValue().set(
        email,
        marketingData
    );
  }
  private MarketingData setWelcomeMail(MarketingData marketingData) {
    marketingData.setWelcomeMail(true);
    return marketingData;
  }

  private MarketingData setNewsMail(MarketingData marketingData) {
    marketingData.setNewsMail(true);
    return marketingData;
  }

}
