package com.fiap.TechChallenge4.Service;

import com.fiap.TechChallenge4.Model.Video;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.gridfs.ReactiveGridFsResource;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDateTime;

public interface VideoService {

    Mono<Video> uploadVideo(Mono<FilePart> file, String titulo, String descricao, String tags);
    Mono<ReactiveGridFsResource> getVideo(String id);
    Mono<Boolean> deleteVideo(String id);
    Flux<Video> getVideos(URI uri, Pageable pageable);
    Flux<Video> getVideosByParameters(URI uri, String name, String tag, LocalDateTime date, Pageable pageable);
}