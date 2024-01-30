package com.fiap.TechChallenge4.Repository;

import com.fiap.TechChallenge4.Model.Favorite;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface FavoritesRepository extends ReactiveMongoRepository<Favorite, String> {
    public Flux<Favorite> findByUserId(String userId);
}
