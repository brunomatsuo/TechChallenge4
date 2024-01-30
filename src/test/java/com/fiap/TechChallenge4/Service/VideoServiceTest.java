package com.fiap.TechChallenge4.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.mongodb.gridfs.ReactiveGridFsOperations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.file.Path;

class VideoServiceTest {

    @Mock
    private ResourceLoader resourceLoader;

    @Mock
    private ReactiveGridFsOperations gridFsOperations;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getVideos() {
    }

    @Test
    void getVideosByParameters() {
    }

    @Test
    void getVideo() {
    }

    @Test
    void deleteVideo() {
    }

    private Mono<Resource> getFilePart() {
        var resource = this.resourceLoader.getResource("classpath:video/190144 (540p).mp4");
        return Mono.fromSupplier(() -> resource);
    }

    private BasicDBObject createDBObject(String titulo, String descricao, String tags) {
        BasicDBObject object = new BasicDBObject();
        object.put("type", "file");
        object.append("title", titulo);
        object.append("description", descricao);
        object.append("tags", tags);

        return object;
    }

    private FilePart createFilePart() {
        return new FilePart() {
            @Override
            public String filename() {
                return null;
            }

            @Override
            public Mono<Void> transferTo(Path dest) {
                return null;
            }

            @Override
            public String name() {
                return null;
            }

            @Override
            public HttpHeaders headers() {
                return null;
            }

            @Override
            public Flux<DataBuffer> content() {
                return null;
            }
        };
    }
    private String getMetadataValue(GridFSFile file, String key) {
        return file.getMetadata().get(key, String.class);
    }
}