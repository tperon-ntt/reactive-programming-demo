package com.example.demo;


import com.example.demo.controller.MarketingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StartCommand implements CommandLineRunner {

  @Autowired
  private MarketingService marketingService;

  @Override
  public void run(String... args) throws Exception {
    marketingService.deleteAll().subscribe();
  }
}
