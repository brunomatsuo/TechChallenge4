package com.fiap.TechChallenge4.Service;

import com.fiap.TechChallenge4.Model.User;
import com.fiap.TechChallenge4.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers() {
        User user1 = newUser("User1", "user1@gmail.com");
        User user2 = newUser("User2", "user2@gmail.com");
        List<User> users = Arrays.asList(user1, user2);

        userRepository.save(user1);
        userRepository.save(user2);

        Mockito.when(userRepository.findAll()).thenReturn(Flux.fromIterable(users));

        Flux<User> result = userRepository.findAll();

        assertEquals(users, result.collectList().block());
    }

    @Test
    void createUser() {
        User user = newUser("Teste", "teste@gmail.com");

        Mockito.when(userRepository.save(user)).thenReturn(Mono.just(user));

        Mono<User> result = userRepository.save(user);

        assertEquals(user, result.block());
    }

    @Test
    void updateUser() {
        User user = newUser("Teste", "teste@gmail.com");
        userRepository.save(user);

        user.setNome("Nome Atualizado");

        Mockito.when(userRepository.findById(user.getId())).thenReturn(Mono.just(user));

        userRepository.save(user);
        Mono<User> result = userRepository.findById(user.getId());

        assertEquals(user, result.block());
    }

    @Test
    void deleteUser() {
        User user = newUser("Teste", "teste@gmail.com");
        userRepository.save(user);

        Mockito.when(userRepository.findById(user.getId())).thenReturn(Mono.empty());

        userRepository.deleteById(user.getId());
        Mono<User> result = userRepository.findById(user.getId());

        assertEquals(Mono.empty(), result);
    }

    @Test
    void findById() {
        User user = newUser("Teste", "teste@gmail.com");
        userRepository.save(user);

        Mockito.when(userRepository.findById(user.getId())).thenReturn(Mono.just(user));

        Mono<User> result = userRepository.findById(user.getId());

        assertEquals(user, result.block());
    }

    private User newUser(String nome, String email) {
        return new User(UUID.randomUUID(), nome, email);
    }
}