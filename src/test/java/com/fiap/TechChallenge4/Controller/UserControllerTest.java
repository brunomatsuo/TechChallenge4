package com.fiap.TechChallenge4.Controller;

import com.fiap.TechChallenge4.Model.DTO.UserDTO;
import com.fiap.TechChallenge4.Model.User;
import com.fiap.TechChallenge4.Repository.UserRepository;
import com.fiap.TechChallenge4.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = UserController.class)
public class UserControllerTest {

    @Autowired
    private WebTestClient client;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private final UserDTO user = new UserDTO( "123", "teste", "teste@gmail.com");


    @BeforeEach
    void setUp(ApplicationContext context) {
        client = WebTestClient.bindToApplicationContext(context).build();
    }

   /* @Test
    void getUsersTest() {
        Mockito.when(userRepository.findAll()).thenReturn(Flux.empty());

        client.get()
                .uri("/user")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void createUserTest() {

         User entity = new User(UUID.randomUUID(), "teste", "teste@gmail.com");

        Mockito.when(userRepository.save(entity)).thenReturn(Mono.empty());

        client.post()
                .uri("/user")
                .bodyValue(user)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void updateUserTest() {
        User entity = new User(UUID.randomUUID(), "teste", "teste@gmail.com");

        Mockito.when(userRepository.findAll()).thenReturn(Flux.empty());
        Mockito.when(userRepository.save(entity)).thenReturn(Mono.empty());

        client.put()
                .uri("/user")
                .bodyValue(user)
                .exchange()
                .expectStatus().isOk();
    }


    @Test
    void deleteUserTest() {
        User entity = new User(UUID.randomUUID(), "teste", "teste@gmail.com");

        Mockito.when(userRepository.findById(UUID.randomUUID())).thenReturn(Mono.just(entity));
        Mockito.when(userRepository.delete(entity)).thenReturn(Mono.empty());

        client.delete()
                .uri("/user/123")
                .exchange()
                .expectStatus().isOk();
    }*/

    @Test
    void getById() {
        Mockito.when(userRepository.findById(UUID.randomUUID())).thenReturn(Mono.empty());

        client.get()
                .uri("/user/123")
                .exchange()
                .expectStatus().isOk();
    }
}