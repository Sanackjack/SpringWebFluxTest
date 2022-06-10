package com.example.springwebfluxtest.user.controller;


import com.example.springwebfluxtest.Entities.Agent;
import com.example.springwebfluxtest.user.service.AgentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
    @RequestMapping("/agent")
    public class AgentController {
        private final AgentService agentService;

        public AgentController(AgentService agentService) {
            this.agentService = agentService;
        }


//    @GetMapping
//    public Flux<ResponseEntity<Agent>> getAllUsers() {
//        return userService.findAllUser()
//                .map(agent -> ResponseEntity.ok(agent))
//                .defaultIfEmpty(ResponseEntity.notFound().build());
//    }

    @GetMapping
    public Flux<Agent> getAllUsers() {
        return agentService.findAllUser();
    }

        @GetMapping("/lastname")
        public Flux<Agent> getUsersByLastname(@RequestParam(name = "last_name", required = false) String lastName) {
            return agentService.findUserByLastName(lastName);
        }

        @GetMapping("/{id}")
        public Mono<ResponseEntity<Agent>> getuser(@PathVariable String id) {
            return agentService.findUserById(id)
                    .map(agent -> ResponseEntity.ok(agent))
                    .defaultIfEmpty(ResponseEntity.notFound().build());
        }

        @PostMapping
        public Mono<Agent> createUser(@RequestBody Agent agent) {
            return agentService.createNewUser(agent);
        }

        @PutMapping("/{id}")
        public Mono<ResponseEntity<Agent>> putuser(@PathVariable String id, @RequestBody Agent agent) {
            return agentService.editUserById(id, agent)
                    .map(ResponseEntity::ok)
                    .defaultIfEmpty(ResponseEntity.notFound().build());
        }

        @DeleteMapping("/{id}")
        public Mono<Void> deleteUser(@PathVariable String id) {
            return agentService.deleteUserById(id);
        }
    }

