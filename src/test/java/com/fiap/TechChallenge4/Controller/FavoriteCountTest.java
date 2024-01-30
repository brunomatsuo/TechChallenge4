package com.fiap.TechChallenge4.Controller;

import com.fiap.TechChallenge4.Model.FavoriteCount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FavoriteCountTest {

    private final FavoriteCount count = new FavoriteCount();

    @Test
    void getVideoId() {
        String id = "id";

        count.setCount(Long.parseLong("2"));
        count.setVideoId(id);

        assertEquals(count.getVideoId(), id);
    }

    @Test
    void getCount() {
        Long number = Long.parseLong("2");

        count.setCount(number);
        count.setVideoId("id");

        assertEquals(count.getCount(), number);
    }

    @Test
    void setVideoId() {
        String id = "id";

        count.setCount(Long.parseLong("2"));
        count.setVideoId(id);

        assertEquals(count.getVideoId(), id);
    }

    @Test
    void setCount() {
        Long number = Long.parseLong("2");

        count.setCount(number);
        count.setVideoId("id");

        assertEquals(count.getCount(), number);
    }
}