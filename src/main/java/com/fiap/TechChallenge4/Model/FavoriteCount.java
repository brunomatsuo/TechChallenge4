package com.fiap.TechChallenge4.Model;

import lombok.Getter;
import lombok.Setter;

public class FavoriteCount {
    @Setter
    @Getter
    private String videoId;
    @Setter
    @Getter
    private Long count;
}
