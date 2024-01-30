package com.fiap.TechChallenge4.Controller;

import com.fiap.TechChallenge4.Service.VideoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.InputStream;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = VideoController.class)
public class VideoControllerTest {

    @Autowired
    private WebTestClient client;

    @MockBean
    private VideoService videoService;

    @BeforeEach
    void setUp(ApplicationContext context) {
        client = WebTestClient.bindToApplicationContext(context).build();
    }

    @Test
    void getVideosTest() {

        client.get()
                .uri("/videos")
                .exchange()
                .expectStatus().isOk();
    }
/*
    @Test
    void uploadFileTest() throws IOException {
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("190144 (540p).mp4");

        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "190144 (540p).mp4",
                MediaType.MULTIPART_FORM_DATA_VALUE,
                resourceAsStream.readAllBytes()
        );

        client.post()
                .uri("/videos")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .bodyValue(file)
                .exchange()
                .expectStatus().isOk();
    }
*/
    @Test
    void viewFileTest() {

        Mockito.when(videoService.getVideo("123")).thenReturn(Mono.empty());

        client.get()
                .uri("/videos/123/view")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void downloadFileTest() {

        Mockito.when(videoService.getVideo("123")).thenReturn(Mono.empty());

        client.get()
                .uri("/videos/123/download")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void deleteFileTest() {

        client.delete()
                .uri("/videos/123")
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    void searchVideosTest() {

        client.get()
                .uri("/videos/search")
                .exchange()
                .expectStatus().isOk();
    }

}