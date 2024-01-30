package com.fiap.TechChallenge4.Service;


import com.fiap.TechChallenge4.Exception.ResourceNotFoundException;
import com.fiap.TechChallenge4.Model.Video;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.ReactiveGridFsOperations;
import org.springframework.data.mongodb.gridfs.ReactiveGridFsResource;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VideoServiceImpl implements VideoService {

    final ReactiveGridFsOperations gridFsOperations;

    @Override
    public Flux<Video> getVideos(URI uri, Pageable pageable) {
        Query query = query(where("metadata.type").is("file"));

        return gridFsOperations.find(query).map(file -> Video.builder()
                .id(file.getObjectId().toString())
                .fileName(file.getFilename())
                .titulo(getMetadataValue(file, "title"))
                .descricao(getMetadataValue(file, "description"))
                .url(MessageFormat.format("{0}/videos/{1}/view", uri, file.getObjectId().toString()))
                .tags(getMetadataValue(file, "tags"))
                .dataPublicacao(file.getUploadDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                .build());
    }

    @Override
    public Flux<Video> getVideosByParameters(URI uri, String name, String tag, LocalDateTime date, Pageable pageable) {
        Query query = query(where("metadata.type").is("file"));
        if(name != null){
            query.addCriteria(where("metadata.title").is(name));
        }
        if(date != null){
            query.addCriteria(where("uploadDate").gte(date));
        }
        if(tag != null){
            query.addCriteria(where("metadata.tags").regex("^.*"+tag+"*"));
        }

        return gridFsOperations.find(query).map(file -> Video.builder()
                .id(file.getObjectId().toString())
                .fileName(file.getFilename())
                .titulo(getMetadataValue(file, "title"))
                .descricao(getMetadataValue(file, "description"))
                .url(MessageFormat.format("{0}/videos/{1}/view", uri, file.getObjectId().toString()))
                .tags(getMetadataValue(file, "tags"))
                .dataPublicacao(file.getUploadDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                .build());
    }

    private String getMetadataValue(GridFSFile file, String key) {
        return file.getMetadata().get(key, String.class);
    }

    @Override
    public Mono<Video> uploadVideo(Mono<FilePart> filePart, String titulo, String descricao, String tags) {
        BasicDBObject object = new BasicDBObject();
        object.put("type", "file");
        object.append("title", titulo);
        object.append("description", descricao);
        object.append("tags", tags);

        return filePart.flatMap(part ->
                gridFsOperations.store(part.content(), part.filename(), object)
                        .map(objectId ->
                                Video.builder()
                                        .id(objectId.toHexString())
                                        .fileName(part.filename())
                                        .titulo(titulo)
                                        .descricao(descricao)
                                        .tags(tags)
                                        .dataPublicacao(objectId.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                                        .build())
        );
    }

    @Override
    public Mono<ReactiveGridFsResource> getVideo(String id) {
        return gridFsOperations.findOne(query(where("_id").is(id)))
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("File", "id", id)))
                .log()
                .flatMap(gridFsOperations::getResource);
    }

    @Override
    public Mono<Boolean> deleteVideo(String id) {

        Query query = query(where("_id").is(id));

        return gridFsOperations.findOne(query)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("File", "id", id)))
                .log()
                .flatMap(file -> gridFsOperations.delete(query))
                .then(Mono.just(true));
    }
}