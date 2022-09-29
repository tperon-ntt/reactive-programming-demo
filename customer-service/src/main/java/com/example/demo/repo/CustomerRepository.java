package com.example.demo.repo;

import com.example.demo.model.CustomerData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CustomerRepository extends ReactiveMongoRepository<CustomerData, String> {

  Mono<CustomerData> findByEmail(String email);

}
