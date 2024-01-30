package com.fiap.TechChallenge4.Controller;

import com.fiap.TechChallenge4.Model.Video;
import com.fiap.TechChallenge4.Service.VideoService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/videos")
public class VideoController {

    final VideoService videoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Video> getVideos(UriComponentsBuilder uriComponentsBuilder,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size){
        URI uri = uriComponentsBuilder
                .replacePath(null)
                .replaceQuery(null)
                .build().toUri();
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "dataPublicacao"));
        return videoService.getVideos(uri, pageable);
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Mono<Video> uploadFile(@RequestPart(name = "file") Mono<FilePart> file, @RequestPart(name = "titulo") String titulo,
                                  @RequestPart(name = "descricao") String descricao, @RequestPart(name = "tags") String tags){
        return videoService.uploadVideo(file, titulo, descricao, tags);
    }

    @GetMapping("/{videoId}/view")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Void> viewFile(
            @PathVariable String videoId,
            ServerWebExchange exchange) {
        return videoService.getVideo(videoId)
                .flatMapMany((resource) -> {
                    exchange.getResponse().getHeaders().set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"");
                    return exchange.getResponse().writeWith(resource.getDownloadStream());
                });
    }

    @GetMapping("/{videoId}/download")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Void> downloadFile(@PathVariable String videoId,
                                   ServerWebExchange exchange) {
        return videoService.getVideo(videoId)
                .flatMapMany((resource) -> {
                    exchange.getResponse().getHeaders().set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");
                    return exchange.getResponse().writeWith(resource.getDownloadStream());
                });
    }

    @DeleteMapping("/{videoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Boolean> deleteFile(@PathVariable String videoId){
        return videoService.deleteVideo(videoId);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Video> searchVideos(UriComponentsBuilder uriComponentsBuilder,
                                 String name,
                                 String tag,
                                 LocalDateTime date,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size){
        URI uri = uriComponentsBuilder
                .replacePath(null)
                .replaceQuery(null)
                .build().toUri();
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "dataPublicacao"));
        return videoService.getVideosByParameters(uri, name, tag, date, pageable);
    }
}
