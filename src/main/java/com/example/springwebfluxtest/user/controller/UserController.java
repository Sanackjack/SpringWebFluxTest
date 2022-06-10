package com.example.springwebfluxtest.user.controller;

import com.example.springwebfluxtest.Entities.UserEntity;
import com.example.springwebfluxtest.Entities.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final DatabaseClient databaseClient;

    private final UserRepository userRepository;

    @GetMapping
    public Flux<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<UserEntity> findById(@PathVariable("id") final UUID id) {
        return userRepository.findById(id);
               // .switchIfEmpty(Mono.error(new NotFoundException("User id \"" + id.toString() + "\"not found")));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Mono<UserEntity> create(@RequestBody final UserEntity entity) {
        entity.setId(UUID.randomUUID());
//        return databaseClient.insert()
//                .into(UserEntity.class)
//                .using(entity)
//                .then()
//                .thenReturn(entity);

//        return  databaseClient.sql("INSERT INTO  UserEntity (username, firstname,lastname) VALUES (:username, :firstname, :lastname)")
//                .filter((statement, executeFunction) -> statement.returnGeneratedValues("id").execute())
//                .bind("username", entity.getUsername())
//                .bind("firstname", entity.getFirstName())
//                .bind("lastname", entity.getLastName())
//                .fetch()
//                .first()
//                .map(r -> (UUID) r.get("id"));
return null;
    }

    @PutMapping("/{id}")
    public Mono<UserEntity> update(@PathVariable("id") final UUID id, @RequestBody final UserEntity entity) {
        return findById(id)
                .flatMap(dbEntity -> {
                    dbEntity.setFirstName(entity.getFirstName());
                    dbEntity.setLastName(entity.getLastName());
                    return userRepository.save(dbEntity);
                });
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public Mono<Void> deleteById(@PathVariable("id") final UUID id) {
        return findById(id)
                .flatMap(dbEntity -> {
                    return userRepository.deleteById(dbEntity.getId());
                });
    }
}