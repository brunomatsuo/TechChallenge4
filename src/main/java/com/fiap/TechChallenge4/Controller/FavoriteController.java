package com.fiap.TechChallenge4.Controller;

import com.fiap.TechChallenge4.Model.Favorite;
import com.fiap.TechChallenge4.Model.FavoriteCount;
import com.fiap.TechChallenge4.Service.FavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/favorite")
public class FavoriteController {

    @Autowired
    FavoritesService favoritesService;

    @PostMapping
    public Mono<Favorite> addFavorite(@RequestBody Favorite favorite) {
        return favoritesService.AddFavorite(favorite);
    }

    @GetMapping("/{userId}")
    public Flux<Favorite> getFavorites(@PathVariable String userId) {
        return favoritesService.getFavorites(userId);
    }

    @GetMapping("/count")
    public List<FavoriteCount> getAll() {
        List<Favorite> favoriteList = favoritesService.getAll().collectList().block();
        return favoritesService.getFavoriteCounts(favoriteList);
    }
}

