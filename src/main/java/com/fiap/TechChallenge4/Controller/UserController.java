package com.fiap.TechChallenge4.Controller;

import com.fiap.TechChallenge4.Model.DTO.UserDTO;
import com.fiap.TechChallenge4.Model.User;
import com.fiap.TechChallenge4.Repository.UserRepository;
import com.fiap.TechChallenge4.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public Flux<UserDTO> getUsers() {
        return userService.getAllUsers().map(UserDTO::ModeltoDTO);
    }

    @PostMapping
    public Mono<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO).map(UserDTO::ModeltoDTO);
    }

    @PutMapping
    public Mono<ResponseEntity<Object>> updateUser(@RequestBody UserDTO userDTO) {
        Mono monoResponse = userService.updateUser(userDTO);
        if(monoResponse.equals(Mono.empty())) {
            return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        }
        return Mono.just(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }

    @DeleteMapping("/{id}")
    public Mono deleteUser(@PathVariable("id") String id) {
        Mono monoResponse = userService.deleteUser(id);
        if(monoResponse.equals(Mono.empty())) {
            return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        }
        return Mono.just(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }

    @GetMapping("/{id}")
    public Mono<User> getById(@PathVariable String id) {
        return userService.findById(id);
    }

}
