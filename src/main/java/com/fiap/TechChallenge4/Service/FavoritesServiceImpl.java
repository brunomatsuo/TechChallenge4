package com.fiap.TechChallenge4.Service;

import com.fiap.TechChallenge4.Model.FavoriteCount;
import com.fiap.TechChallenge4.Model.Favorite;
import com.fiap.TechChallenge4.Repository.FavoritesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FavoritesServiceImpl implements FavoritesService {

    @Autowired
    FavoritesRepository favoritesRepository;

    @Override
    public Mono<Favorite> AddFavorite(Favorite favorite) {
        return favoritesRepository.save(favorite);
    }

    @Override
    public Flux<Favorite> getFavorites(String userId) {
        return favoritesRepository.findByUserId(userId);
    }

    @Override
    public Flux<Favorite> getAll() {
        return favoritesRepository.findAll();
    }

    @Override
    public List<FavoriteCount> getFavoriteCounts(List<Favorite> favoriteList) {
        Map<String, List<Favorite>> favoritesGrouped = favoriteList.stream().collect(Collectors.groupingBy(x -> x.getVideoId()));
        Set<String> videosIds = favoritesGrouped.keySet();
        Iterator<String> videosIterator = videosIds.iterator();

        List<FavoriteCount> counter = new ArrayList<FavoriteCount>();

        while(videosIterator.hasNext()) {
            String current = videosIterator.next();
            FavoriteCount count = new FavoriteCount();
            List<Favorite> values = favoritesGrouped.get(current);
            count.setCount(values.stream().count());
            count.setVideoId(current);

            counter.add(count);
        }
        return counter;
    }
}
