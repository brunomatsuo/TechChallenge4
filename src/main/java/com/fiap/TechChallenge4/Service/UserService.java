package com.fiap.TechChallenge4.Service;

import com.fiap.TechChallenge4.Model.DTO.UserDTO;
import com.fiap.TechChallenge4.Model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    public Flux<User> getAllUsers();
    public Mono<User> createUser(UserDTO userDTO);
    public Mono<User> updateUser(UserDTO userDTO);
    public Mono deleteUser(String id);
    public Mono<User> findById(String id);
}
