package com.fiap.TechChallenge4.Model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class VideoTest {

    private final Video video = new Video("1", "teste.mp4", "Vídeo Teste", "Vídeo para testes", "http://localhost:8080/videos/1/view", LocalDateTime.now(), "teste, novo, erro");

    private final Video builder = Video.builder()
            .id("AA1B2")
            .fileName("teste.mp4")
            .titulo("Vídeo Teste")
            .descricao("Vídeo para testes")
            .tags("teste, novo, erro")
            .dataPublicacao(LocalDateTime.now())
            .build();

    @Test
    void shouldGetId() {
        assertThat(video.getId()).isNotNull();
    }

    @Test
    void shouldGetFileName() {
        assertThat(video.getFileName()).isEqualTo("teste.mp4");
    }

    @Test
    void shouldGetTitulo() {
        assertThat(video.getTitulo()).isEqualTo("Vídeo Teste");
    }

    @Test
    void shouldGetDescricao() {
        assertThat(video.getDescricao()).isEqualTo("Vídeo para testes");
    }

    @Test
    void shouldGetUrl() {
        assertThat(video.getUrl()).isEqualTo("http://localhost:8080/videos/1/view");
    }

    @Test
    void shouldGetDataPublicacao() {
        assertThat(video.getDataPublicacao()).isBeforeOrEqualTo(LocalDateTime.now());
    }

    @Test
    void shouldGetTags() {
        assertThat(video.getTags()).isEqualTo("teste, novo, erro");
    }

    @Test
    void shouldSetFileNameToNoArgsConstructor() {
        Video video = new Video();
        video.setFileName( "novo.mp4");

        assertThat(video.getFileName()).isNotNull();
        assertThat(video.getFileName()).isEqualTo( "novo.mp4");
    }

    @Test
    void shouldSetFileNameToBuilder() {
        assertThat(builder.getFileName()).isNotNull();
        assertThat(builder.getFileName()).isEqualTo( "teste.mp4");
    }


    @Test
    void shouldSetFileName() {
        String newFileName = "novo.mp4";

        video.setFileName(newFileName);

        assertThat(video.getFileName()).isEqualTo(newFileName);
    }

    @Test
    void shouldSetTitulo() {
        String newTitulo = "Novo Título";

        video.setTitulo(newTitulo);

        assertThat(video.getTitulo()).isEqualTo(newTitulo);
    }

    @Test
    void shouldSetDescricao() {
        String newDescricao = "Nova Descrição";

        video.setDescricao(newDescricao);

        assertThat(video.getDescricao()).isEqualTo(newDescricao);
    }

    @Test
    void shouldSetUrl() {
        String newUrl = "http://localhost:8080/videos/10/view";

        video.setUrl(newUrl);

        assertThat(video.getUrl()).isEqualTo(newUrl);
    }

    @Test
    void shouldSetDataPublicacao() {
        LocalDateTime newDate = LocalDateTime.now().plusDays(1);

        video.setDataPublicacao(newDate);

        assertThat(video.getDataPublicacao()).isBeforeOrEqualTo(LocalDateTime.now().plusDays(1));
    }

    @Test
    void shouldSetTags() {
        String newTags = "new, old, none";

        video.setTags(newTags);

        assertThat(video.getTags()).isEqualTo(newTags);
    }
}