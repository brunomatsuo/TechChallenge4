package com.fiap.TechChallenge4.Service;

import com.fiap.TechChallenge4.Model.FavoriteCount;
import com.fiap.TechChallenge4.Model.Favorite;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface FavoritesService {
    public Mono<Favorite> AddFavorite(Favorite favorite);
    public Flux<Favorite> getFavorites(String userId);
    public Flux<Favorite> getAll();
    public List<FavoriteCount> getFavoriteCounts(List<Favorite> favoriteList);
}
