package com.example.demo.config;

import com.example.demo.model.MarketingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

  @Autowired
  private RedisConnectionFactory factory;

  @Bean
  public ReactiveRedisTemplate<String, MarketingData> reactiveRedisTemplate(ReactiveRedisConnectionFactory factory) {

    Jackson2JsonRedisSerializer<MarketingData> serializer = new Jackson2JsonRedisSerializer<>(MarketingData.class);

    RedisSerializationContext.RedisSerializationContextBuilder<String, MarketingData> builder =
        RedisSerializationContext.newSerializationContext(new StringRedisSerializer());

    RedisSerializationContext<String, MarketingData> context = builder.value(serializer)
        .build();

    return new ReactiveRedisTemplate<>(factory, context);
  }
}