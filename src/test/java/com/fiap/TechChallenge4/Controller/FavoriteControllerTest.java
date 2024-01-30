package com.fiap.TechChallenge4.Controller;

import com.fiap.TechChallenge4.Model.Favorite;
import com.fiap.TechChallenge4.Service.FavoritesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = FavoriteController.class)
public class FavoriteControllerTest {

    @Autowired
    private WebTestClient client;

    @MockBean
    private FavoritesService favoritesService;

    @BeforeEach
    void setUp(ApplicationContext context) {
        client = WebTestClient.bindToApplicationContext(context).build();
    }

    @Test
    void addFavoriteTest() {

        Favorite favorite = new Favorite("123", "teste", "123");

        client.post()
                .uri("/favorite")
                .bodyValue(favorite)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void getFavoritesTest() {
        client.get()
                .uri("/favorite/123")
                .exchange()
                .expectStatus().isOk();
    }

}