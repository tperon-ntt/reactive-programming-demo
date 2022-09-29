package com.example.demo;


import com.example.demo.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class StartCommand implements CommandLineRunner {

  @Autowired
  private CustomerRepository customerRepository;

  @Override
  public void run(String... args) throws Exception {
    customerRepository.deleteAll().subscribe( );
  }
}
