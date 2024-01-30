package com.fiap.TechChallenge4.Service;

import com.fiap.TechChallenge4.Model.DTO.UserDTO;
import com.fiap.TechChallenge4.Model.User;
import com.fiap.TechChallenge4.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Flux<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Mono<User> createUser(UserDTO userDTO) {
        User user = UserDTO.DTOtoModel(userDTO);
        user.setId(UUID.randomUUID());
        return userRepository.save(user);
    }

    @Override
    public Mono updateUser(UserDTO userDTO) {
        final Mono<User> user = userRepository.findById(UUID.fromString(userDTO.getId()));
        if(Objects.isNull(user)) {
            return Mono.empty();
        }
        User updatedUser = UserDTO.DTOtoModel(userDTO);
        return userRepository.findById(updatedUser.getId()).switchIfEmpty(Mono.empty()).filter(Objects::nonNull).flatMap(userToBeUpdated -> userRepository
                .save(updatedUser).then(Mono.just(updatedUser)));
    }

    @Override
    public Mono deleteUser(String id) {
        final Mono<User> user = userRepository.findById(UUID.fromString(id));
        if(Objects.isNull(user)) {
            return Mono.empty();
        }
        return userRepository.findById(UUID.fromString(id)).switchIfEmpty(Mono.empty()).filter(Objects::nonNull).flatMap(userToBeDeleted -> userRepository
                .delete(userToBeDeleted).then(Mono.just(userToBeDeleted)));
    }

    @Override
    public Mono<User> findById(String id) {
        Mono<User> user = userRepository.findById(UUID.fromString(id));
        return user;
    }
}
