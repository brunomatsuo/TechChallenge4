package com.fiap.TechChallenge4.Model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    private final User user = new User(UUID.randomUUID(), "Usuario Teste", "teste@email.com");

    @Test
    void shouldGetId() {
        assertThat(user.getId()).isNotNull();
    }

    @Test
    void shoultSetNomeToNoArgsConstructor() {
        User user = new User();
        user.setNome("Usuario Teste");

        assertThat(user.getNome()).isNotNull();
        assertThat(user.getNome()).isEqualTo("Usuario Teste");
    }

    @Test
    void shouldGetNome() {
        assertThat(user.getNome()).isNotNull();
        assertThat(user.getNome()).isEqualTo("Usuario Teste");
    }

    @Test
    void shouldGetEmail() {
        assertThat(user.getEmail()).isNotNull();
        assertThat(user.getEmail()).isEqualTo("teste@email.com");
    }

    @Test
    void shouldSetNome() {
        String newName = "Novo Nome";

        user.setNome(newName);

        assertThat(user.getNome()).isEqualTo(newName);
    }

    @Test
    void shouldSetEmail() {
        String newEmail = "novoemail@email.com";

        user.setEmail(newEmail);

        assertThat(user.getEmail()).isEqualTo(newEmail);
    }
}