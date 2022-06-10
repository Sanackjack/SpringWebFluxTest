package com.example.springwebfluxtest.user.service;

import com.example.springwebfluxtest.Entities.Agent;
import com.example.springwebfluxtest.Entities.AgentRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AgentService {
    private final AgentRepository agentRepository;

    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    public Mono<Agent> findUserById(String id) {
        return agentRepository.findById(id);
    }

    public Flux<Agent> findUserByLastName(String lastName) {

            return agentRepository.findByLastName(lastName);

    }

    public Flux<Agent> findAllUser() {

        return agentRepository.findAll();

    }

    public Mono<Agent> createNewUser(Agent Agent) {

        //**** Repository save(…) with an associated ID completes with TransientDataAccessException if the row does not exist in the database.
         return agentRepository.save(Agent);

//        agentRepository.
//        return agentRepository.findOldest()
//                .flatMap(user -> {              // process the response from DB
//                    user.setTheOldest(true);
//                    return userRepository.save(user);
 //               });
    }

    public Mono<Agent> editUserById(String id, Agent Agent) {
        return agentRepository.findById(id)
                .flatMap(e -> {
                    Agent.setId(e.getId());
                    return agentRepository.save(Agent);
                });

    }

    public Mono<Void> deleteUserById(String id) {
        return agentRepository.deleteById(id);
    }
}
