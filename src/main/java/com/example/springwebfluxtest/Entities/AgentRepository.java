package com.example.springwebfluxtest.Entities;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface AgentRepository extends ReactiveCrudRepository<Agent, String> {
    @Query("SELECT * FROM user WHERE last_name = :lastname")
    Flux<Agent> findByLastName(String lastName);
}